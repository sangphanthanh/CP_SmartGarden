# invoke SourceDir generated makefile for hello.pem3
hello.pem3: .libraries,hello.pem3
.libraries,hello.pem3: package/cfg/hello_pem3.xdl
	$(MAKE) -f C:\Users\AM\SmartGarden\hello_CC1310_LAUNCHXL_TI_CC1310F128/src/makefile.libs

clean::
	$(MAKE) -f C:\Users\AM\SmartGarden\hello_CC1310_LAUNCHXL_TI_CC1310F128/src/makefile.libs clean

