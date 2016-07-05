/*
 * Copyright (c) 2015-2016, Texas Instruments Incorporated
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * *  Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * *  Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * *  Neither the name of Texas Instruments Incorporated nor the names of
 *    its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 *  ======== empty.c ========
 */
/* XDCtools Header files */
#include <xdc/std.h>
#include <xdc/runtime/System.h>

/* BIOS Header files */
#include <ti/sysbios/BIOS.h>
#include <ti/sysbios/knl/Clock.h>
#include <ti/sysbios/knl/Task.h>

/* TI-RTOS Header files */
// #include <ti/drivers/I2C.h>
#include <ti/drivers/PIN.h>
// #include <ti/drivers/SPI.h>
#include <ti/drivers/UART.h>
#include <ti/drivers/i2c/I2CCC26XX.h>
#include <ti/boards/CC1350_LAUNCHXL/Board.h>
// #include <ti/drivers/Watchdog.h>

/* Board Header files */
#include "Board.h"
#include "driverlib/i2c.h"
#include "driverlib/uart.h"
#include "driverlib/prcm.h"
#include "driverlib/sys_ctrl.h"
/* Display */
#include <ti/mw/display/Display.h>


#define TASKSTACKSIZE   512
#define AM2320_ADDR 0x5C
//Start measurement at 1lx resolution. Time typically 16ms.
#define FUNCTION_CODE 0x03



Task_Struct task0Struct;
Char task0Stack[TASKSTACKSIZE];

/* Pin driver handle */
static PIN_Handle ledPinHandle;
static PIN_State ledPinState;

/*
 * Application LED pin configuration table:
 *   - All LEDs board LEDs are off.
 */
PIN_Config ledPinTable[] = {
    Board_LED0 | PIN_GPIO_OUTPUT_EN | PIN_GPIO_LOW | PIN_PUSHPULL | PIN_DRVSTR_MAX,
    Board_LED1 | PIN_GPIO_OUTPUT_EN | PIN_GPIO_LOW | PIN_PUSHPULL | PIN_DRVSTR_MAX,
    PIN_TERMINATE
};

#define NO_INTERFACE 0xFF
/*---------------------------------------------------------------------------*/
static uint8_t slave_addr = 0x00;
static uint8_t interface = NO_INTERFACE;
/*---------------------------------------------------------------------------*/

static bool
accessible(void)
{
  /* First, check the PD */
  if(PRCMPowerDomainStatus(PRCM_DOMAIN_SERIAL)
     != PRCM_DOMAIN_POWER_ON) {
    return false;
  }

  /* Then check the 'run mode' clock gate */
  if(!(HWREG(PRCM_BASE + PRCM_O_I2CCLKGR) & PRCM_I2CCLKGR_CLK_EN)) {
    return false;
  }

  return true;
}
/*---------------------------------------------------------------------------*/
void
board_i2c_wakeup()
{
  /* First, make sure the SERIAL PD is on */
  PRCMPowerDomainOn(PRCM_DOMAIN_SERIAL);
  while((PRCMPowerDomainStatus(PRCM_DOMAIN_SERIAL)
        != PRCM_DOMAIN_POWER_ON));

  /* Enable the clock to I2C */
  PRCMPeripheralRunEnable(PRCM_PERIPH_I2C0);
  PRCMLoadSet();
  while(!PRCMLoadGet());

  /* Enable and initialize the I2C master module */
  I2CMasterInitExpClk(I2C0_BASE, SysCtrlClockGet(),
                                 true);
}
/*---------------------------------------------------------------------------*/
static bool
i2c_status()
{
  uint32_t status;

  status = I2CMasterErr(I2C0_BASE);
  if(status & (I2C_MSTAT_DATACK_N_M | I2C_MSTAT_ADRACK_N_M)) {
    I2CMasterControl(I2C0_BASE, I2C_MASTER_CMD_BURST_SEND_ERROR_STOP);
  }

  return status == I2C_MASTER_ERR_NONE;
}
/*---------------------------------------------------------------------------*/
void
board_i2c_shutdown()
{
  interface = NO_INTERFACE;

  if(accessible()) {
    I2CMasterDisable(I2C0_BASE);
  }

  PRCMPeripheralRunDisable(PRCM_PERIPH_I2C0);
  PRCMLoadSet();
  while(!PRCMLoadGet());

//  /*
//   * Set all pins to GPIO Input and disable the output driver. Set internal
//   * pull to match external pull
//   *
//   * SDA and SCL: external PU resistor
//   * SDA HP and SCL HP: MPU PWR low
//   */
//  IOCPinTypeGpioInput(BOARD_IOID_SDA_HP);
//  IOCIOPortPullSet(BOARD_IOID_SDA_HP, IOC_IOPULL_DOWN);
//  IOCPinTypeGpioInput(BOARD_IOID_SCL_HP);
//  IOCIOPortPullSet(BOARD_IOID_SCL_HP, IOC_IOPULL_DOWN);
//
//  IOCPinTypeGpioInput(BOARD_IOID_SDA);
//  IOCIOPortPullSet(BOARD_IOID_SDA, IOC_IOPULL_UP);
//  IOCPinTypeGpioInput(BOARD_IOID_SCL);
//  IOCIOPortPullSet(BOARD_IOID_SCL, IOC_IOPULL_UP);
}
/*---------------------------------------------------------------------------*/
bool
board_i2c_write(uint8_t *data, uint8_t len)
{
  uint32_t i;
  bool success;

  /* Write slave address */
  I2CMasterSlaveAddrSet(I2C0_BASE, slave_addr, false);

  /* Write first byte */
  I2CMasterDataPut(I2C0_BASE, data[0]);

  /* Check if another master has access */
  while(I2CMasterBusBusy(I2C0_BASE));

  /* Assert RUN + START */
  I2CMasterControl(I2C0_BASE, I2C_MASTER_CMD_BURST_SEND_START);
  while(I2CMasterBusy(I2C0_BASE));
  success = i2c_status();

  for(i = 1; i < len && success; i++) {
    /* Write next byte */
    I2CMasterDataPut(I2C0_BASE, data[i]);
    if(i < len - 1) {
      /* Clear START */
      I2CMasterControl(I2C0_BASE, I2C_MASTER_CMD_BURST_SEND_CONT);
      while(I2CMasterBusy(I2C0_BASE));
      success = i2c_status();
    }
  }

  /* Assert stop */
  if(success) {
    /* Assert STOP */
    I2CMasterControl(I2C0_BASE, I2C_MASTER_CMD_BURST_SEND_FINISH);
    while(I2CMasterBusy(I2C0_BASE));
    success = i2c_status();
    while(I2CMasterBusBusy(I2C0_BASE));
  }

  return success;
}
/*---------------------------------------------------------------------------*/
void board_i2c_write_single(uint8_t i2c_device_addr, uint8_t funcCode, bool bitRate)
{
	/* Initialize the display and try to open both UART and LCD types of display. */
	Display_Params dparams;
	Display_Params_init(&dparams);
	dparams.lineClearMode = DISPLAY_CLEAR_BOTH;
	Display_Handle uartDisplayHandle = Display_open(Display_Type_UART,
			&dparams);

	/* Write slave address */
	I2CMasterSlaveAddrSet(I2C0_BASE, i2c_device_addr, bitRate);

	/* Write first byte */
	I2CMasterDataPut(I2C0_BASE, funcCode);

	/* Assert RUN + START + STOP */
	I2CMasterControl(I2C0_BASE, I2C_MASTER_CMD_SINGLE_SEND);
	while(I2CMasterBusy(I2C0_BASE));
	System_printf("Write Single Device Successful");
	CPUdelay(16000000 / 3 / 40);

}
/*---------------------------------------------------------------------------*/
uint32_t board_i2c_read(uint8_t i2c_device_addr, uint8_t bitRead) {
	/* Initialize the display and try to open both UART and LCD types of display. */
	Display_Params dparams;
	Display_Params_init(&dparams);
	dparams.lineClearMode = DISPLAY_CLEAR_BOTH;
	Display_Handle uartDisplayHandle = Display_open(Display_Type_UART,
			&dparams);
	uint32_t ambLight;

	/* Set slave address */
	I2CMasterSlaveAddrSet(I2C0_BASE, i2c_device_addr, bitRead);

	/* Assert RUN + START + ACK */
	I2CMasterControl(I2C0_BASE, I2C_MASTER_CMD_BURST_RECEIVE_START);

	while (I2CMasterBusy(I2C0_BASE));
	//High byte was pulled from the slave
	ambLight = I2CMasterDataGet(I2C0_BASE);
	ambLight <<= 8;
	System_printf("Hight_Byte = %d; ", ambLight);
	CPUdelay(16000000 / 10 / 40);
	//send control byte and read data from slave
	I2CMasterControl(I2C0_BASE, I2C_MASTER_CMD_BURST_RECEIVE_FINISH);

	//Low byte was pulled from the slave
	uint32_t temp_Low = I2CMasterDataGet(I2C0_BASE);
	ambLight |= temp_Low;
	System_printf("Low_Byte = %d; ", temp_Low);

	return ambLight;



/*  i = 0;
  success = true;
  while(i < (len - 1) && success) {
    while(I2CMasterBusy(I2C0_BASE));
    success = i2c_status();
    if(success) {
      data[i] = I2CMasterDataGet(I2C0_BASE);
      I2CMasterControl(I2C0_BASE, I2C_MASTER_CMD_BURST_RECEIVE_CONT);
      i++;
    }
  }*/

 /* if(success) {
    I2CMasterControl(I2C0_BASE, I2C_MASTER_CMD_BURST_RECEIVE_FINISH);
    while(I2CMasterBusy(I2C0_BASE));
    success = i2c_status();
    if(success) {
      data[len - 1] = I2CMasterDataGet(I2C0_BASE);
      while(I2CMasterBusBusy(I2C0_BASE));
    }
  }
  return success;*/
}

/*---------------------------------------------------------------------------*/
bool
board_i2c_write_read(uint8_t *wdata, uint8_t wlen, uint8_t *rdata, uint8_t rlen)
{
  uint32_t i;
  bool success;

  /* Set slave address for write */
  I2CMasterSlaveAddrSet(I2C0_BASE, slave_addr, false);

  /* Write first byte */
  I2CMasterDataPut(I2C0_BASE, wdata[0]);

  /* Check if another master has access */
  while(I2CMasterBusBusy(I2C0_BASE));

  /* Assert RUN + START */
  I2CMasterControl(I2C0_BASE, I2C_MASTER_CMD_BURST_SEND_START);
  while(I2CMasterBusy(I2C0_BASE));
  success = i2c_status();

  for(i = 1; i < wlen && success; i++) {
    /* Write next byte */
    I2CMasterDataPut(I2C0_BASE, wdata[i]);
    if(i < wlen - 1) {
      /* Clear START */
      I2CMasterControl(I2C0_BASE, I2C_MASTER_CMD_BURST_SEND_CONT);
      while(I2CMasterBusy(I2C0_BASE));
      success = i2c_status();
    }
  }
  if(!success) {
    return false;
  }

  /* Set slave address for read */
  I2CMasterSlaveAddrSet(I2C0_BASE, slave_addr, true);

  /* Assert ACK */
  I2CMasterControl(I2C0_BASE, I2C_MASTER_CMD_BURST_RECEIVE_START);

  i = 0;
  while(i < (rlen - 1) && success) {
    while(I2CMasterBusy(I2C0_BASE));
    success = i2c_status();
    if(success) {
      rdata[i] = I2CMasterDataGet(I2C0_BASE);
      I2CMasterControl(I2C0_BASE, I2C_MASTER_CMD_BURST_RECEIVE_CONT);
      i++;
    }
  }

  if(success) {
    I2CMasterControl(I2C0_BASE, I2C_MASTER_CMD_BURST_RECEIVE_FINISH);
    while(I2CMasterBusy(I2C0_BASE));
    success = i2c_status();
    if(success) {
      rdata[rlen - 1] = I2CMasterDataGet(I2C0_BASE);
      while(I2CMasterBusBusy(I2C0_BASE));
    }
  }

  return success;
}
/*---------------------------------------------------------------------------*/
void
board_i2c_select(uint8_t new_interface, uint8_t address)
{
  slave_addr = address;

  if(accessible() == false) {
    board_i2c_wakeup();
  }

  if(new_interface != interface) {
    interface = new_interface;

    I2CMasterDisable(I2C0_BASE);

//    if(interface == BOARD_I2C_INTERFACE_0) {
//      IOCIOPortPullSet(BOARD_IOID_SDA, IOC_NO_IOPULL);
//      IOCIOPortPullSet(BOARD_IOID_SCL, IOC_NO_IOPULL);
//      ti_lib_ioc_pin_type_i2c(I2C0_BASE, BOARD_IOID_SDA, BOARD_IOID_SCL);
//      IOCPinTypeGpioInput(BOARD_IOID_SDA_HP);
//      IOCPinTypeGpioInput(BOARD_IOID_SCL_HP);
//    } else if(interface == BOARD_I2C_INTERFACE_1) {
//      IOCIOPortPullSet(BOARD_IOID_SDA_HP, IOC_NO_IOPULL);
//      IOCIOPortPullSet(BOARD_IOID_SCL_HP, IOC_NO_IOPULL);
//      ti_lib_ioc_pin_type_i2c(I2C0_BASE, BOARD_IOID_SDA_HP, BOARD_IOID_SCL_HP);
//      IOCPinTypeGpioInput(BOARD_IOID_SDA);
//      IOCPinTypeGpioInput(BOARD_IOID_SCL);
//    }

    /* Enable and initialize the I2C master module */
    I2CMasterInitExpClk(I2C0_BASE, 16000000,
                                   true);
  }
}
/*---------------------------------------------------------------------------*/
/** @} */

// Get BH1750
void getBh1750(UArg arg0, UArg arg1) {
	/* Initialize the display and try to open both UART and LCD types of display. */
	Display_Params dparams;
	Display_Params_init(&dparams);
	dparams.lineClearMode = DISPLAY_CLEAR_BOTH;
	Display_Handle uartDisplayHandle = Display_open(Display_Type_UART,
			&dparams);

	board_i2c_wakeup();
	I2CMasterInitExpClk();
//	uint32_t ambLight = 0;
//	board_i2c_write_single(AM2320_ADDR,FUNCTION_CODE, I2C_400kHz);
//	while (1) {
//		ambLight = board_i2c_read(AM2320_ADDR, true);
//		ambLight /= 1.2;
//		Display_print1(uartDisplayHandle, 0, 0, "%d; ", ambLight);
//		System_printf("ambLigh1.2 = %d \r\n", ambLight);
//	}
}

/*
 *  ======== main ========
 */
int main(void) {
	Task_Params taskParams;
	/* Call board init functions */
	Board_initGeneral();
	// Board_initI2C();
	// Board_initSPI();
	// Board_initUART();
	// Board_initWatchdog();

	/* Construct heartBeat Task  thread */
	Task_Params_init(&taskParams);
	taskParams.arg0 = 1000000 / Clock_tickPeriod;
	taskParams.stackSize = TASKSTACKSIZE;
	taskParams.stack = &task0Stack;
	Task_construct(&task0Struct, (Task_FuncPtr) getBh1750, &taskParams,
			NULL);




//    Task_Params taskParams;
//
//    /* Call board init functions */
//    Board_initGeneral();
//    // Board_initI2C();
//    // Board_initSPI();
//    // Board_initUART();
//    // Board_initWatchdog();
//
//    /* Construct heartBeat Task  thread */
//    Task_Params_init(&taskParams);
//    taskParams.arg0 = 1000000 / Clock_tickPeriod;
//    taskParams.stackSize = TASKSTACKSIZE;
//    taskParams.stack = &task0Stack;
//    Task_construct(&task0Struct, (Task_FuncPtr)heartBeatFxn, &taskParams, NULL);
//
    /* Open LED pins */
    ledPinHandle = PIN_open(&ledPinState, ledPinTable);
    if(!ledPinHandle) {
        System_abort("Error initializing board LED pins\n");
    }

    PIN_setOutputValue(ledPinHandle, Board_LED1, 1);

    System_printf("Starting the example\nSystem provider is set to SysMin. "
                  "Halt the target to view any SysMin contents in ROV.\n");
    /* SysMin will only print to the console when you call flush or exit */
    System_flush();

    /* Start BIOS */
    BIOS_start();
    return (0);

}
