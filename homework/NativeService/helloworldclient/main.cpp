#include	<sys/types.h>
#include	<unistd.h>
#include	<grp.h>

#include	<binder/IPCThreadState.h>
#include	<binder/ProcessState.h>
#include	<binder/IServiceManager.h>
#include	<utils/Log.h>

#include	"IHelloWorld.hh"

int		main(int argc, char **argv)
{
  android::sp<android::IServiceManager> sm = android::defaultServiceManager();
  android::sp<android::IBinder> binder;
  android::HelloWorldProxy *proxy;
  do {
    binder = sm->getService(android::String16(HELLOWORLD_NAME));
    if (binder != 0)
      break;
    usleep(500000); 
  } while(true);
  
  proxy = new android::HelloWorldProxy();
  proxy->setBinder(binder);
  proxy->sayHello();
  printf("sent.\n");
  return(0);
}
