#/bin/bash

PROJ_HOME='/home/binu/playground/hypersonic'
#echo $PROJ_HOME

OUT_FILE='tools/Player.java'
#echo $OUT_FILE

PLAYER_FILE='tools/template/Player.java'

SRC_MAIN='src/main/java'

outFilePath=$PROJ_HOME/$OUT_FILE
playerFilePath=$PROJ_HOME/$PLAYER_FILE
srcMainPath=$PROJ_HOME/$SRC_MAIN

cat $playerFilePath > $outFilePath
sed -i '1,2d' $outFilePath
for i in `find $srcMainPath -type f | grep java$`
do
    echo "File names "$i
    cat $i | grep -v import | grep -v package | sed -e 's/public class/class/g' -e 's/public enum/enum/g' -e 's/public interface/interface/g' -e 's/public abstract/abstract/g' -e 's/@Nullable//g' -e 's/@Deprecated//g' -e 's/@NotNull//g'>> $outFilePath
done

javac -d /tmp $outFilePath




