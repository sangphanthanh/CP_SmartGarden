## THIS IS A GENERATED FILE -- DO NOT EDIT
.configuro: .libraries,em3 linker.cmd package/cfg/hello_pem3.oem3

# To simplify configuro usage in makefiles:
#     o create a generic linker command file name 
#     o set modification times of compiler.opt* files to be greater than
#       or equal to the generated config header
#
linker.cmd: package/cfg/hello_pem3.xdl
	$(SED) 's"^\"\(package/cfg/hello_pem3cfg.cmd\)\"$""\"C:/Users/AM/SmartGarden/hello_CC1310_LAUNCHXL_TI_CC1310F128/.config/xconfig_hello/\1\""' package/cfg/hello_pem3.xdl > $@
	-$(SETDATE) -r:max package/cfg/hello_pem3.h compiler.opt compiler.opt.defs