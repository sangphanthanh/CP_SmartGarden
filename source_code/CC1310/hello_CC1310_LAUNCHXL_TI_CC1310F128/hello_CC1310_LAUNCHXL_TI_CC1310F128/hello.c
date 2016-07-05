
/*
 *  ======== empty.c ========
 */
/* XDCtools Header files */
#include <xdc/std.h>
#include <xdc/runtime/System.h>

/* BIOS Header files */
#include <ti/sysbios/BIOS.h>
#include <ti/sysbios/knl/Task.h>

/* TI-RTOS Header files */

#include <ti/drivers/PIN.h>
#include <ti/drivers/pin/PINCC26XX.h>
#include <ti/drivers/Power.h>
#include <ti/drivers/power/PowerCC26XX.h>
#include <ti/drivers/UART.h>
#include <ti/drivers/I2C.h>
#include <ti/drivers/PIN.h>
// #include <ti/drivers/SPI.h>
// #include <ti/drivers/UART.h>
// #include <ti/drivers/Watchdog.h>
#include  "driverlib/aux_wuc.h"
#include "inc/hw_aux_evctl.h"
#include "driverlib/aux_adc.h"

#include <ti/drivers/Power.h>
#include <ti/drivers/power/PowerCC26XX.h>
/* Board Header files */
#include "Board.h"

/* XDCtools Header files */
#include <xdc/std.h>
#include <xdc/runtime/System.h>

#include <ti/sysbios/knl/Semaphore.h>
#include <ti/sysbios/knl/Clock.h>
#include <ti/sysbios/family/arm/m3/Hwi.h>


#include <xdc/std.h>
#include <xdc/runtime/System.h>

/* Display */
#include <ti/mw/display/Display.h>

/* Pin driver handles */
static PIN_Handle dioPinHandle;

/* Global memory storage for a PIN_Config table */
static PIN_State dioPinState;

/*
 * Initial LED pin configuration table
 *   - LEDs Board_LED0 is on.
 *   - LEDs Board_LED1 is off.
 */
PIN_Config dioPinTable[] = {
	Board_DIO12 | PIN_GPIO_OUTPUT_EN | PIN_GPIO_LOW  | PIN_PUSHPULL | PIN_DRVSTR_MAX,
    PIN_TERMINATE
};


#define TASKSTACKSIZE   512
#define TASK_PRI        1
Task_Struct task0Struct;
Char task0Stack[TASKSTACKSIZE];
uint16_t singleSample;
unsigned long int avgValuePH, avgValueSM;  //Store the average value of the sensor feedback
int phValue, smValue;
int bufSM[10], bufPH[10], tempSM, tempPH;
int i,j;

long map(long x, long in_min, long in_max, long out_min, long out_max)
{
  return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
}

void readSMPH(UArg a0, UArg a1) {
	// Initialize the display and try to open both UART and LCD types of display.
	Display_Params dparams;
	Display_Params_init(&dparams);
	dparams.lineClearMode = DISPLAY_CLEAR_BOTH;
	Display_Handle uartDisplayHandle = Display_open(Display_Type_UART,
			&dparams);
	while (1) {
		uint8_t lux1;
		uint8_t lux2;
		uint16_t lux;
		uint8_t txBuffer[1];
		uint8_t rxBuffer[2];
		I2C_Handle i2c;
		I2C_Params i2cParams;
		I2C_Transaction i2cTransaction;

		//Create I2C for usage
		I2C_Params_init(&i2cParams);
		i2cParams.bitRate = I2C_400kHz;
		i2c = I2C_open(CC1310_LAUNCHXL_I2C0, &i2cParams);
		if (i2c == NULL) {
			System_abort("Error Initializing I2C\n");
		} else {
			System_printf("I2C Initialized!\n");
		}

		//Task_sleep(1000000 / Clock_tickPeriod);

		txBuffer[0] = 0x10;

		i2cTransaction.writeCount = 1;
		i2cTransaction.writeBuf = txBuffer;
		i2cTransaction.readCount = 2;
		i2cTransaction.readBuf = rxBuffer;
		i2cTransaction.slaveAddress = 0x23; //OPT3001 ADDR;

		if (I2C_transfer(i2c, &i2cTransaction)) {

			//Extract degrees C from the received data
			lux1 = rxBuffer[0];

			lux2 = rxBuffer[1];

			lux = ((lux1 << 8) | lux2) / 1.2;
			System_printf("Lux: %d \t \n", lux);
			Display_print1(uartDisplayHandle, 0, 0, "Pkts sent: %u", lux);
		} else {
			System_printf("I2C Bus fault\n");
		}


		//Enable Clock AUX
		AUXWUCClockEnable(AUX_WUC_MODCLKEN0_ANAIF_M | AUX_WUC_MODCLKEN0_AUX_ADI4_M);
		//Configuration AUX_ADC
		AUXADCEnableSync(AUXADC_REF_FIXED, AUXADC_SAMPLE_TIME_341_US,
		AUXADC_TRIGGER_MANUAL);
		//Congiguration Input Pin DIO23
		AUXADCSelectInput(ADC_COMPB_IN_AUXIO7);

		for (i = 0; i < 10; i++) //Get 10 sample value from the sensor for smooth the value
				{
			//trigger and get value from AUX_ADC
			AUXADCGenManualTrigger();
			bufSM[i] = AUXADCReadFifo();
		}
		//AUX_ADC disable
		AUXADCDisable();


		//Enable Clock AUX
		AUXWUCClockEnable(AUX_WUC_MODCLKEN0_ANAIF_M | AUX_WUC_MODCLKEN0_AUX_ADI4_M);
		//Configuration AUX_ADC
		AUXADCEnableSync(AUXADC_REF_FIXED, AUXADC_SAMPLE_TIME_341_US,
		AUXADC_TRIGGER_MANUAL);
		//Congiguration Input Pin DIO24
		AUXADCSelectInput(ADC_COMPB_IN_AUXIO6);

		for (i = 0; i < 10; i++) //Get 10 sample value from the sensor for smooth the value
				{
			//trigger and get value from AUX_ADC
			AUXADCGenManualTrigger();
			bufPH[i] = AUXADCReadFifo();
		}
		//AUX_ADC disable
		AUXADCDisable();

		for (i = 0; i < 9; i++)        //sort the analog from small to large
				{
			for (j = i + 1; j < 10; j++) {
				if (bufSM[i] > bufSM[j]) {
					tempSM = bufSM[i];
					bufSM[i] = bufSM[j];
					bufSM[j] = tempSM;
				}

				if (bufPH[i] > bufPH[j]) {
									tempPH = bufPH[i];
									bufPH[i] = bufPH[j];
									bufPH[j] = tempPH;
								}
			}
		}
		avgValueSM = 0;
		avgValuePH = 0;
		for (i = 2; i < 8; i++) {    //take the average value of 6 center sample
			avgValueSM += bufSM[i];
		}

		for (i = 2; i < 8; i++) {    //take the average value of 6 center sample
					avgValuePH += bufPH[i];
				}

		phValue = (float)(((avgValuePH * 5)/6.0)/950); //convert the analog into millivolt
		smValue = map((avgValueSM/6),0,3600,100,0); //convert the analog into millivolt

		if(smValue<50){
			PIN_setOutputValue(dioPinHandle, Board_DIO12, 1);
		} else if(smValue>80){
			PIN_setOutputValue(dioPinHandle, Board_DIO12, 0);
		}

		Display_print1(uartDisplayHandle, 0, 0, "SM: %u",smValue);
		Display_print1(uartDisplayHandle, 0, 0, "pH: %u",phValue);
		System_printf("Value SM: %d \t \n", smValue);
		System_printf("Value PH: %d \t \n", phValue);

		//Deinitialized I2C
		I2C_close(i2c);
		System_printf("I2C closed!\n");


		System_flush();
	}
}

/*void readPH(UArg a0, UArg a1) {

	while (1) {
		//Enable Clock AUX
		AUXWUCClockEnable(AUX_WUC_MODCLKEN0_ANAIF_M | AUX_WUC_MODCLKEN0_AUX_ADI4_M);
		//Configuration AUX_ADC
		AUXADCEnableSync(AUXADC_REF_FIXED, AUXADC_SAMPLE_TIME_1P37_MS,
		AUXADC_TRIGGER_MANUAL);
		//Congiguration Input Pin DIO23
		AUXADCSelectInput(ADC_COMPB_IN_AUXIO7);

		for (i = 0; i < 10; i++) //Get 10 sample value from the sensor for smooth the value
				{
			//trigger and get value from AUX_ADC
			AUXADCGenManualTrigger();
			buf[i] = AUXADCReadFifo();
		}
		//AUX_ADC disable
		AUXADCDisable();

		for (i = 0; i < 9; i++)        //sort the analog from small to large
				{
			for (j = i + 1; j < 10; j++) {
				if (buf[i] > buf[j]) {
					temp = buf[i];
					buf[i] = buf[j];
					buf[j] = temp;
				}
			}
		}
		avgValue = 0;
		for (i = 2; i < 8; i++) {    //take the average value of 6 center sample
			avgValue += buf[i];
		}
		phValue = (float)(((avgValue * 5)/6.0)/950); //convert the analog into millivolt
		//phValue = 3.5 * phValue;           //convert the millivolt into pH value
		System_printf("Value: %d \t \n", phValue);
		System_flush();
	}


}*/
/*
 *  ======== main ========
 */


int main(void)
{
	Task_Params taskParams;

	// Call board init functions
	Board_initGeneral();
	// Board_initI2C();
	// Board_initSPI();
	// Board_initUART();
	// Board_initWatchdog();

	/* Open LED pins */
	dioPinHandle = PIN_open(&dioPinState, dioPinTable);
	if (!dioPinHandle) {
		System_abort("Error initializing board LED pins\n");
	}

	//  Construct heartBeat Task  thread
	Task_Params_init(&taskParams);
	taskParams.arg0 = 1000;
	taskParams.stackSize = TASKSTACKSIZE;
	taskParams.stack = &task0Stack;
	taskParams.instance->name = "readSMPH";
	System_printf("Task Init Ok \n");
	Task_construct(&task0Struct, (Task_FuncPtr) readSMPH, &taskParams, NULL);
	System_flush();

	/* Start BIOS */
	BIOS_start();

	return (0);
}
