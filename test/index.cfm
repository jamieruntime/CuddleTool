<cfscript>
writeAuthor();

function writeAuthor(boolean output)
{
	if(arguments.output)
	{
		writeOutput(new subfolder().getAuthor());
	}
	else
	{
		writeDump(new subfolder().getAuthor());
	}
}
</cfscript>