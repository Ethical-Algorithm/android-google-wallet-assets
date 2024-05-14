echo "Setup Git Packages User and Token"
touch local.properties

file="./local.properties"
if [ -f "$file" ]; then
  echo "$file found."

  echo "gitHubUser=jobernas" >> "$file"
  echo "gitHubToken=$token" >> "$file"

else
  echo "$file not found."
fi