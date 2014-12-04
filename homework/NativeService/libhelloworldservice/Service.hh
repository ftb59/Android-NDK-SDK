#ifndef		__HELLO_WORLD_HH__
#define		__HELLO_WORLD_HH__

#include	<utils/KeyedVector.h>
#include	<utils/RefBase.h>
#include	<binder/IInterface.h>
#include	<binder/Parcel.h>
#include	<utils/String16.h>
#include	<utils/threads.h>
#include	<string.h>

#include	<utils/Log.h>

#include	"IHelloWorld.hh"

#define HELLOWORLD_NAME "cn.bjtu.helloworldservice.IHelloWorld"

namespace android
{
  class Service : public BnInterface<IHelloWorld>
  {
  public:
    virtual		~Service(){};
    static void		instantiate();
    void		sayHello(const char *str);
    status_t		onTransact(uint32_t code, const android::Parcel &data,
				   Parcel *reply, uint32_t flags);
  private:
    Service(){};
  };
}

#endif
