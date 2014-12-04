#ifndef		__IHELLO_WORLD_H__
#define		__IHELLO_WORLD_H__

#include	<binder/IInterface.h>

#define HELLOWORLD_NAME "cn.bjtu.helloworldservice.IHelloWorld"

namespace android
{
  class IHelloWorld: public IInterface
  {
  protected:
    enum {
      HW_SAYHELLO =  IBinder::FIRST_CALL_TRANSACTION
    };
  public:
    virtual void sayHello(const char *str) = 0;
    DECLARE_META_INTERFACE(HelloWorld)
  };

    class HelloWorldProxy
    {
    private:
      android::sp<android::IHelloWorld>   target;
    public:
      HelloWorldProxy(){};
      void setBinder(const sp<IBinder>& impl);
      void sayHello();
    };
}

#endif
