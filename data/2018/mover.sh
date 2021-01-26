#!/bin/tcsh

## Problem name
P="s5"
#D=samples
D=subtask1
TAG="1-"


foreach f ($D/??)
echo $f
OUTPUT=$(echo $f| cut -d'/' -f2)
cp $f $P.$TAG$OUTPUT.in
end

foreach f ($D/??.a)
echo $f
OUTPUT=$(echo $f| cut -d'/' -f2|cut -d'.' -f1)
cp $f $P.$TAG$OUTPUT.out
end

