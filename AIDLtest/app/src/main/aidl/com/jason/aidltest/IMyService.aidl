// IMyService.aidl
package com.jason.aidltest;


// Interface declartion
interface IMyService {
    //You con pass the value of in, out or inout
    //The primitive types (int, boolean, etc) are only passed by in

    int add(in int value1, in int value2);

}
