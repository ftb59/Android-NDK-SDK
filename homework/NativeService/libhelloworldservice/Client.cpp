#define LOG_TAG "HelloWorldService-JNI"

#include <utils/Log.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <dirent.h>
#include <unistd.h>
#include <string.h>
#include <cutils/atomic.h>
#include <utils/Errors.h>
#include <binder/IServiceManager.h>
#include <utils/String16.h>
#include <binder/Parcel.h>

#include "IHelloWorld.hh"
#include "utils/Log.h"

namespace android
{
  class BpHelloWorld: public BpInterface<IHelloWorld>
  {
  public:
    BpHelloWorld(const sp<IBinder>& impl): BpInterface<IHelloWorld>(impl){}
   
    virtual void	sayHello(const char *str)
    {
      Parcel	data;
      Parcel	reply;

      data.writeInterfaceToken(getInterfaceDescriptor());
      data.writeString16(String16(str));
      remote()->transact(HW_SAYHELLO, data, &reply, IBinder::FLAG_ONEWAY);
    }
  };
 
  void HelloWorldProxy::setBinder(const sp<IBinder>& impl)
    {
      target = interface_cast<IHelloWorld>(impl);
    }

  void HelloWorldProxy::sayHello()
    {
      target->sayHello("Hello World!");
    }
  IMPLEMENT_META_INTERFACE(HelloWorld, HELLOWORLD_NAME);
};
