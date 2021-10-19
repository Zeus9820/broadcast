import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(Parent());
}

class Parent extends StatefulWidget {

  @override
  _ParentState createState() => _ParentState();
}

class _ParentState extends State<Parent> {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home:Child(),
    );
  }
}

class Child extends StatefulWidget {

  @override
  _ChildState createState() => _ChildState();
}

class _ChildState extends State<Child> {

  static const platform = MethodChannel('samples.flutter.dev/broadcast');
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar:AppBar(title:Text("Welcome to Broadcast")),
      body:const FlatButton(
        child: Text(""),
        onPressed: getResult,
      ),
    );
  }
}

void getResult()
{
  Future result;
  try {
    result= _ChildState.platform.invokeMethod("registeredReceiver");
    print(result);
  } on PlatformException catch (e) {
    result = "Failed to get battery level: '${e.message}'." as Future;
  }



}


