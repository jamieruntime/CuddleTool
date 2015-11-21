Cuddle Tool
===========

Version 1.0.0
Jamie Purchase
20/11/2015

Description
-----------

Converts script files with braces how I write them, like this;

<blockquote>
public string function getHello(required string name)<br>
{<br>
&nbsp;&nbsp;&nbsp;&nbsp;return "Hello " & arguments.name;<br>
}
</blockquote>

...to cuddled braces (how my team like them), like this;

<blockquote>
public string function getHello(required string name) {<br>
&nbsp;&nbsp;&nbsp;&nbsp;return "Hello " & arguments.name;<br>
}
</blockquote>

Options
-------

* directory - path to the directory (eg: "C:\Users\Jamie\Documents\")
* extension - files of this extension will be changed (eg: "txt" will change text files)
* subfolders - true or false if change files in subfolders

Running the Tool
----------------

open command prompt and launch CuddleTool.jar passing the three arguments above

to get output back in command prompt, run the jar like this
<blockquote>
	>java -jar CuddleTool.jar "path" "extension" false
</blockquote>

![cmd](https://github.com/JamieRuntime/CuddleTool/blob/master/images/cmd.png?raw=true "Command Prompt")

About the Author
----------------

You can learn more at my site: <a href = "https://jamieruntime.wordpress.com/" target = "_blank">JamieRuntime</a>