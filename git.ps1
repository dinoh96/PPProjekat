param (
	[string]$message = "New commit"
)


git add .
git commit -m $message
git push -u origin main