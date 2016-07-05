################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Each subdirectory must supply rules for building sources it contributes
CC1310_LAUNCHXL.obj: ../CC1310_LAUNCHXL.c $(GEN_OPTS) $(GEN_HDRS)
	@echo 'Building file: $<'
	@echo 'Invoking: ARM Compiler'
	"C:/ti/ccsv6/tools/compiler/ti-cgt-arm_16.3.0.STS/bin/armcl" -mv7M3 --code_state=16 --float_support=vfplib -me --include_path="C:/Users/AM/SmartGarden/hello_CC1310_LAUNCHXL_TI_CC1310F128" --include_path="C:/ti/tirtos_cc13xx_cc26xx_2_16_01_14/products/cc13xxware_2_03_02_16941" --include_path="C:/ti/ccsv6/tools/compiler/ti-cgt-arm_16.3.0.STS/include" -g --define=ccs --display_error_number --diag_wrap=off --diag_warning=225 --diag_warning=255 --gen_func_subsections=on --abi=eabi --preproc_with_compile --preproc_dependency="CC1310_LAUNCHXL.d" $(GEN_OPTS__FLAG) "$<"
	@echo 'Finished building: $<'
	@echo ' '

ccfg.obj: ../ccfg.c $(GEN_OPTS) $(GEN_HDRS)
	@echo 'Building file: $<'
	@echo 'Invoking: ARM Compiler'
	"C:/ti/ccsv6/tools/compiler/ti-cgt-arm_16.3.0.STS/bin/armcl" -mv7M3 --code_state=16 --float_support=vfplib -me --include_path="C:/Users/AM/SmartGarden/hello_CC1310_LAUNCHXL_TI_CC1310F128" --include_path="C:/ti/tirtos_cc13xx_cc26xx_2_16_01_14/products/cc13xxware_2_03_02_16941" --include_path="C:/ti/ccsv6/tools/compiler/ti-cgt-arm_16.3.0.STS/include" -g --define=ccs --display_error_number --diag_wrap=off --diag_warning=225 --diag_warning=255 --gen_func_subsections=on --abi=eabi --preproc_with_compile --preproc_dependency="ccfg.d" $(GEN_OPTS__FLAG) "$<"
	@echo 'Finished building: $<'
	@echo ' '

hello.obj: ../hello.c $(GEN_OPTS) $(GEN_HDRS)
	@echo 'Building file: $<'
	@echo 'Invoking: ARM Compiler'
	"C:/ti/ccsv6/tools/compiler/ti-cgt-arm_16.3.0.STS/bin/armcl" -mv7M3 --code_state=16 --float_support=vfplib -me --include_path="C:/Users/AM/SmartGarden/hello_CC1310_LAUNCHXL_TI_CC1310F128" --include_path="C:/ti/tirtos_cc13xx_cc26xx_2_16_01_14/products/cc13xxware_2_03_02_16941" --include_path="C:/ti/ccsv6/tools/compiler/ti-cgt-arm_16.3.0.STS/include" -g --define=ccs --display_error_number --diag_wrap=off --diag_warning=225 --diag_warning=255 --gen_func_subsections=on --abi=eabi --preproc_with_compile --preproc_dependency="hello.d" $(GEN_OPTS__FLAG) "$<"
	@echo 'Finished building: $<'
	@echo ' '

configPkg/linker.cmd: ../hello.cfg
	@echo 'Building file: $<'
	@echo 'Invoking: XDCtools'
	"C:/ti/xdctools_3_32_00_06_core/xs" --xdcpath="C:/ti/tirtos_cc13xx_cc26xx_2_16_01_14/packages;C:/ti/tirtos_cc13xx_cc26xx_2_16_01_14/products/tidrivers_cc13xx_cc26xx_2_16_01_13/packages;C:/ti/tirtos_cc13xx_cc26xx_2_16_01_14/products/bios_6_45_02_31/packages;C:/ti/tirtos_cc13xx_cc26xx_2_16_01_14/products/uia_2_00_05_50/packages;C:/ti/ccsv6/ccs_base;" xdc.tools.configuro -o configPkg -t ti.targets.arm.elf.M3 -p ti.platforms.simplelink:CC1310F128 -r release -c "C:/ti/ccsv6/tools/compiler/ti-cgt-arm_16.3.0.STS" --compileOptions "-mv7M3 --code_state=16 --float_support=vfplib -me --include_path=\"C:/Users/AM/SmartGarden/hello_CC1310_LAUNCHXL_TI_CC1310F128\" --include_path=\"C:/ti/tirtos_cc13xx_cc26xx_2_16_01_14/products/cc13xxware_2_03_02_16941\" --include_path=\"C:/ti/ccsv6/tools/compiler/ti-cgt-arm_16.3.0.STS/include\" -g --define=ccs --display_error_number --diag_wrap=off --diag_warning=225 --diag_warning=255 --gen_func_subsections=on --abi=eabi  " "$<"
	@echo 'Finished building: $<'
	@echo ' '

configPkg/compiler.opt: | configPkg/linker.cmd
configPkg/: | configPkg/linker.cmd


