import 'dart:isolate';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

Future<void> main() async{
  WidgetsFlutterBinding.ensureInitialized();
  // await Firebase.initializeApp();
  //  FirebaseMessaging.instance.getToken().then((value) => print("shaimaaToken : ${value}"));
  // // Set the background messaging handler early on, as a named top-level function
  //  FirebaseMessaging.onBackgroundMessage(_firebaseMessagingBackgroundHandler);
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home:  MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

var platform =  const MethodChannel('flutter.native/helper');

class MyHomePage extends StatefulWidget {

  MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {

  _init() async{
    var result =  await preventScreenshots();
    ScaffoldMessenger.of(context).showSnackBar(SnackBar(content: Text(result)));
  }
  @override
  void initState() {
    _init();
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Isolates')),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            //const CircularProgressIndicator(),
            const SizedBox(height: 50),
            ElevatedButton(
              child: const Text('TAKE SCREENSHOT'),
              onPressed: () async{
              },
              //onPressed: () => NotificationsHelper().showNotification(null),
            ),
          ],
        ),
      ),
    );
  }
  Future<String> preventScreenshots() async {
    String output;
    var result;
    try {
      result = await platform.invokeMethod('preventScreenshots');
      output = 'preventScreenshots scessfully';
    } on PlatformException catch (e) {
      output = "Failed to preventScreenshots: '${e.message}'.";
    }
    return result;
  }
}
