productionJS
============

*Application in JAVA for concatenate and minify JS*

`productionJS` is a little JAVA application made to facilitate your production of JS. It uses YUICompress to minify JS.

##Getting Started
`productionJS` need Java 1.7

##Usage
To use `productionJS`, change the configuration settings.json an launch the .jar (with a doucle-click or in console).

```sh
java -jar ProductionJS.jar
```

##Example of configuration
```javascript
{
    "prior": [
        "/Users/yourname/Documents/websites/yourwebsite/in1/script1.js",
        "/Users/yourname/Documents/websites/yourwebsite/in1/script2.js"
    ],
    "in": [
        "/Users/yourname/Documents/websites/yourwebsite/in1",
        "/Users/yourname/Documents/websites/yourwebsite/in2"
    ],
    "out": "/Users/yourname/Documents/websites/yourwebsite/out",
    "minify": false,
    "license":"/*Copyright (C) 2014 Flavien Collomb Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the 'Software'), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions: The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software. THE SOFTWARE IS PROVIDED 'AS IS', WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.*/"
}
```

The final out must be different from the "in" folders

##Advantages
1. This application can be used to concatenate other files
2. Execution can be done on several folders
3. Priority dependencies can be specified

## License
`productionJS` is licensed under the MIT license. (http://opensource.org/licenses/MIT)
