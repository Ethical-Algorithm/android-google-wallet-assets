setProperty(){
  awk -v pat="^$1=" -v value="$1=$2" '{ if ($0 ~ pat) print value; else print $0; }' $3 > $3.tmp
  mv $3.tmp $3
}

echo "Increase build and version Number"

file="./gradle.properties"
if [ -f "$file" ]; then
  echo "$file found."

  echo "Saving Version =$moduleVersion - Build Number ="$moduleBuild
  setProperty "versionName" "$moduleVersion" "$file"
  setProperty "versionBuild" "$moduleBuild" "$file"

else
  echo "$file not found."
fi