import React, { useState } from 'react';
import BackButton from '../../../components/backbutton';
import './ParentingTips.css'

import Babypeed from '../../../assets/parentips/babypeed.jpg'
import Babyplay from '../../../assets/parentips/babyplay.jpg'
import Babysleep from '../../../assets/parentips/babysleep.jpg'
import Babytalk from '../../../assets/parentips/babytalk.jpg'

const ParentingTips = () => {
  const [currentTip, setCurrentTip] = useState('tip1');

  const renderTip = () => {
    switch (currentTip) {
        case 'tip1':
            return <Tip1 />;
        case 'tip2':
            return <Tip2 />;
        case 'tip3':
            return <Tip3 />;
        case 'tip4':
            return <Tip4 />;
        default:
            return <Tip1 />;
    }
  };

  return (
    <div>
      <div className='parentip-top'>
        <BackButton/>
        <div className='parentip-title'>육아 정보</div>
      </div>
      <div className='parentip-header'>
        <button onClick={() => setCurrentTip('tip1')}>수면</button>
        <button onClick={() => setCurrentTip('tip2')}>수유</button>
        <button onClick={() => setCurrentTip('tip3')}>놀이</button>
        <button onClick={() => setCurrentTip('tip4')}>소통</button>
      </div>
      <div>{renderTip()}</div>
    </div>
  );
};

const Tip1 = () => (
  <div>
    <img src={Babysleep} className='baby-tip-photo' />
    <div className='tip-months'>1~3개월</div>
    <div className='tip-text'>이 시기에 아기는 낮과 밤에 주로 구분없이 대부분의 시간을 잠을 자고 24시간 중 약 16시간을 잠을 잡니다.</div>
    <div className='tip-text'>아기의 수면에는 깊은 잠과 얕은 잠이 있습니다. 얕은 잠을 잘 때 몸을 움직임이거나 비틀거나 소스라치는 듯한 모습을 볼 수 있고, 쉽게 깰 수 있습니다. 조용하고 고르게 호흡을 할 때는 깊은 잠을 자는 것입니다.</div>
    <div className='tip-text'>아기는 잘 때 얕은 잠과 깊은 잠을 거치는 수면주기를 반복하게 됩니다. 이 수면주기는 대략 40분입니다. 한 수면주기가 끝날 때 아기들은 잠시 동안 깹니다. 이렇게 깼을 때, 보채거나 울 수도 있으므로 양육자는 다음 수면주기에 적응하도록 도와주어야 합니다.</div>
    <div className='tip-text'>약 2-3개월 정도가 되면 아기는 밤과 낮의 수면 패턴이 발달되기 시작합니다. 이 때 아기는 밤에 더 많이 자게 됩니다.</div>
    <div className='tip-text'>3개월이 되면서 아기는 자는 중에 덜 깨고 다시 안정을 취할 수 있게 됩니다. 이 시기 아기들은 저녁에 더 오래 잠을 자는데 약 4-5시간 정도입니다.</div>
    <div className='tip-months'>4~6개월</div>
    <div className='tip-text'>이 시기 아기들은 24시간 중 10-18시간을 잔다. 평균적으로 약 14시간 잡니다.</div>
    <div className='tip-text'>아기들은 최대 2시간씩 두 세 번씩 자는 낮잠의 패턴을 형성하기 시작합니다.</div>
    <div className='tip-text'>저녁 수면이 길어집니다. 예를 들어, 아기가 6개월이 될 때 저녁 6시간 동안의 긴 수면을 취하기도 합니다. 하지만 여전히 저녁에 적어도 한번 깰 수 있습니다.</div>
    <div className='tip-text'>이 시기에도 여전히 먹이기 위해 아기를 깨울 필요가 있습니다.</div>
    <div className='tip-text'>대부분의 아기들은 잠을 청하는데 도움이 필요합니다.</div>
    <div className='tip-text'>낮 동안에 아기가 낮잠을 잘 때 조용하고 어둡게 해줍니다. 아기가 클수록 깨어있는 시간이 많아지면서 시끄럽거나 환한 곳에서는 자려고 하지 않습니다.</div>
    <div className='tip-months'>7~9개월</div>
    <div className='tip-sub-title '>수면시간</div>
    <div className='tip-text'>월령이 커가면서 아기의 잠이 줄어듭니다. 아기가 12개월까지 24시간 중 14~16시간의 수면이 필요합니다.</div>
    <div className='tip-text'>약 6개월에서 대부분의 아기는 밤에 오래 잠을 잡니다.</div>
    <div className='tip-text'>대부분의 아기는 오후 6시~8시에 잠자리에 들 수 있습니다. 보통 잠을 자는데 30분도 걸리지 않지만 10명 중 1명은 더 오래 걸리기도 합니다.</div>
    <div className='tip-text'>이 시기에 대부분의 아기들은 여전히 1~2회 낮잠을 잡니다. 이 낮잠은 보통 1~2시간 정도입니다. 일부 아기는 더 오래 잠을 자지만 최대 1/4의 아이가 한 시간 미만으로 낮잠을 잔다고 합니다.</div>
  </div>
);

const Tip2 = () => (
  <div>
    <img src={Babypeed} className='baby-tip-photo' />
    <div className='tip-months'>1~3개월</div>
    <div className='tip-text'>모유수유를 하는 경우, 한쪽 젖을 각각 10~15분 정도 먹으며, 하루에 8~12번(2~3시간 마다 수유)정도 먹다가 2개월이 지나면 5~8회 정도 먹습니다. 모유수유 중인 아기는 1~6개월까지 하루 평균 750cc-800cc의 모유를 섭취합니다. 그러나 이것은 아기마다 다를 수 있습니다. 모유를 먹이는 경우 아기가 2~3시간 정도의 간격으로 젖을 먹고 잘 잔다면 모유량에 대하여 너무 걱정하지 않으셔도 좋습니다.</div>
    <div className='tip-text'>분유를 먹이는 경우 3개월이 될 때까지 체중(kg) 당 150cc가 필요합니다. 일부 아기들은 체중(kg)당 최대 200cc의 분유가 필요할 수 있는데 특히 조산아일 경우 그렇습니다. 따라서 체중이 4kg인 아기가 1개월이 되면 600-800cc를 먹일 수 있습니다. 분유를 먹이는 경우에는 하루에 아기가 얼마나 먹는지 기록합니다. 위 연령에 따른 수유량에 대한 정보는 단지 가이드일 뿐입니다. 아기에게 꼭 맞지 않을 수도 있습니다.</div>
    <div className='tip-months'>4~6개월</div>
    <div className='tip-text'>출산 후 되도록 빨리(30분~1시간 이내) 모유 수유를 시작하고, 생후 6개월간은 아기에게 모유만을 먹이고 6개월 이후부터는 이유식을 첨가하는 게 바람직하다고 권하고 있습니다. 모유수유 중인 아기는 1~6개월까지 하루 평균 750cc-800cc의 모유를 섭취합니다. 그러나 이것은 아기마다 다를 수 있습니다.</div>
    <div className='tip-months'>7~9개월</div>
    <div className='tip-sub-title'>모유수유</div>
    <div className='tip-text'>세계보건기구에서는 2세까지 모유 수유를 권장합니다.</div>
    <div className='tip-sub-title'>분유수유</div>
    <div className='tip-text'>6~12개월 사이의 아기는 체중(kg) 당 약 90-100cc의 분유가 필요합니다.</div>
  </div>
);

const Tip3 = () => (
  <div>
    <img src={Babyplay} className='baby-tip-photo' />
    <div className='tip-months'>1~3개월</div>
    <div className='tip-text'>부모가 하는 모든 활동이 아기가 배우는 데에 도움이 됩니다. 아기는 모든 시간동안 배우고 있기 때문에 아기를 가르치기 위해 특별히 시간을 낼 필요가 없습니다. 아기는 엄마와 함께 있는 것만으로도 가장 잘 배우고 있으며 새로운 경험을 할 기회를 갖는 것입니다. 수유, 기저귀 교환, 목욕시간과 같이 일과 속에서 아기가 배우고 놀 수 있도록 아기에게 말을 걸고 아기의 반응을 기다려 봅니다. 아기는 엄마의 이야기에 몸을 움직여 반응한다는 것을 기억하세요.</div>
    <div className='tip-sub-title'>엎드려 놀기</div>
    <div className='tip-text'>하루 중 잠깐이라도 아기를 다른 자세로 눕혀 놓는 것이 아기의 근육을 강화하는 데에 중요합니다. 아기의 배가 바닥을 향하도록 엎어 놓음으로써 아기가 머리를 들도록 하고 아기의 목과 등 근육을 강화시킬 수 있습니다. 등을 바닥에 대고 눕히는 것은 아기로 하여금 발을 움직이고 다리를 차도록 만듭니다. 또한 등을 바닥에 향하게 눕히면 아기는 머리를 옆으로 움직이면서 물체를 따라갈 수 있습니다. 여러 가지 다른 위치로 아이를 눕힘으로써 아기가 다른 세상을 보게 하고 아기가 환경을 보다 잘 탐색할 수 있도록 합니다.</div>
    <div className='tip-months'>4~6개월</div>
    <div className='tip-sub-title'>까꿍놀이</div>
    <div className='tip-text'>4개월이 되면 아기들은 초보적인 대상영속성 기술을 지니게 되므로 까꿍 놀이를 하면 즐거워할 수 있습니다. 대상영속성이란 사물을 만질 수 없고, 소리를 들을 수 없고, 볼 수 없을 경우에도 사물이 존재한다는 것을 알고 있음을 나타내는 용어입니다.</div>
    <div className='tip-sub-title'>다양한 소리 들려주기</div>
    <div className='tip-text'>동작을 곁들여서 노래와 자장가를 불러줍니다. 뮤직박스나 소리나는 장난감을 아기 앞에 놓고 작동시키세요. 딸랑이를 아기 시선의 약간 바깥에서 흔들어 아기가 고개를 소리나는 쪽으로 돌리게 해보세요.</div>
    <div className='tip-sub-title'>촉감놀이</div>
    <div className='tip-text'>다양한 소재로 만들어진 옷을 아기에게 입힙니다. 아기가 그것을 느낄 수 있도록 아기의 손으로 만지게 해주세요. 아기를 마사지해주세요. 이는 엄마와 아기 모두에게 즐거운 일입니다.</div>
    <div className='tip-sub-title'>책 읽기</div>
    <div className='tip-text'>아기에게 책을 읽어주는 것이 너무 이른 것은 아닙니다. 아기와 함께 책을 읽는 것은 아기의 다양한 기술을 발달시키는 데에 도움이 됩니다. 이 시기 아기들은 크고 밝은 그림이 있는 책을 좋아하 것이며 두꺼운 종이 책이 천으로 된 책보다 낫습니다. 두꺼운 페이지는 아기가 쉽게 넘길 수 있고, 쉽게 닦거나 씻을 수 있기 때문입니다.</div>
  </div>
);

const Tip4 = () => (
    <div>
      <img src={Babytalk} className='baby-tip-photo' />
      <div className='tip-months'>1~3개월</div>
      <div className='tip-sub-title'>애착형성</div>
      <div className='tip-text'>영아기에는 적절한 반응을 통해 엄마와의 신뢰감을 형성하는 것이 가장 중요한 과업입니다. 이 신호에 대해 양육자가 재빠르고 정확하게 눈치 채고 요구에 적절히 반응해 주면, 아이는 이러한 보호와 사랑, 접촉의 경험으로 세상이 자신을 환영하는 곳임을 인식하게 되고 주 양육자와 특별한 정서적 유대감을 형성하게 됩니다.</div>
      <div className='tip-sub-title'>애착형성의 3요소</div>
      <div className='tip-text'>애착형성을 해야 하는 영아기에 양육자가 보여야 할 가장 중요한 3가지 태도는 민감성, 반응성, 일관성입니다.</div>
      <div className='tip-text'>민감성이란 아이가 보내는 신호를 빨리 알아채고, 즉각적으로 반응을 해주는 것을 말합니다.</div>
      <div className='tip-text'>반응성이란 아기가 울면 바로 반응해 주는 것입니다.</div>
      <div className='tip-text'>일관성이란 아기가 엄마의 반응을 예측할 수 있게 하는 것입니다.</div>
      <div className='tip-months'>4~6개월</div>
      <div className='tip-sub-title'>아기신호 이해하기</div>
      <div className='tip-text'>아기가 몸으로 하는 이야기, 즉 아기가 보내는 신호를 잘 살피면 아기의 기분이 어떻고, 무엇을 원하는지를 알 수 있습니다.</div>
      <div className='tip-text'>아기는 몸으로 피곤한지, 배가 고픈지, 깨서 놀고 싶은지, 쉬고 싶은지를 말해줍니다.</div>
      <div className='tip-text'>아기가 보내는 신호를 잘 알아차려서 반응을 해주면 아기는 더욱 편안하고 안전하게 느낍니다. 이런 느낌으로 돌봐주는 사람과의 관계가 더욱 돈독해질 수 있습니다.</div>
      <div className='tip-text'>아기가 보내는 신호에 민감하게 반응하면서 상호작용을 하게 되면 돌보는 일이 즐거운 일이 되고 아기와 정서적 유대감을 형성할 수 있습니다.</div>
    </div>
  );

export default ParentingTips;
