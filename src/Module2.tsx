import {ImageBackground, Text, View} from 'react-native';

export function Module2() {
  const bgImageSource = require('./resource/membership_header.png');
  return (
    <View>
      <ImageBackground
        style={{width: '100%', height: '100%'}}
        source={bgImageSource}>
        <Text>这是模块2</Text>
      </ImageBackground>
    </View>
  );
}
