/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/victor/AndroidstudioProjects/UdacitySunNewsApp/udacitySunNewsApp/src/main/aidl/com/nectar/thesun/IRemoteService.aidl
 */
package com.nectar.thesun;
public interface IRemoteService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.nectar.thesun.IRemoteService
{
private static final java.lang.String DESCRIPTOR = "com.nectar.thesun.IRemoteService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.nectar.thesun.IRemoteService interface,
 * generating a proxy if needed.
 */
public static com.nectar.thesun.IRemoteService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.nectar.thesun.IRemoteService))) {
return ((com.nectar.thesun.IRemoteService)iin);
}
return new com.nectar.thesun.IRemoteService.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_getCounter:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getCounter();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.nectar.thesun.IRemoteService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public int getCounter() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getCounter, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_getCounter = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public int getCounter() throws android.os.RemoteException;
}
