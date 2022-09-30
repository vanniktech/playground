package com.vanniktech.playground

enum class MyEnum {
  FOO,
  BAR,
}

fun print(myEnum: MyEnum) {
  when (myEnum) {
    MyEnum.FOO -> print("Foo")
    MyEnum.BAR -> error("Not supported.")
  }

  when (myEnum) {
    MyEnum.FOO -> print("Foo")
  }
}
