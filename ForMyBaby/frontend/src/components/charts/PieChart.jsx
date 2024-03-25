// import styled from 'styled-components';
// import { USER_CHART_LEGEND_AUTH } from 'app.features/user/constants/userDashboardChartLegends';
// import { Tooltip } from 'antd';
// import UserChartLegend from './UserChartLegend';
// import {
//   PieChart,
//   Pie,
//   Cell,
//   Legend,
//   Tooltip as RechartsTooltip,
//   ResponsiveContainer,
// } from 'recharts';

// // 컬러 상수
// const COLORS_DIC = {
//   y: '#5893E1',
//   n: '#FD9179',
// };

// const UserAuthChart = ({ data }) => {
//   if (!data) return null;

//   const { total, data: datasource } = data?.snsCertified;

//   return (
//     <StyledWrapper>
//       <div className="user-dashboard-card-title small">
//         <Tooltip
//           title={<CutomTooltip />}
//           color={'white'}
//           overlayInnerStyle={{
//             padding: '16px',
//             color: '#000',
//             fontSize: '12px',
//             borderRadius: '4px',
//           }}
//         >
//           <span className="cursor">계(SNS 가입 회원 수):</span>
//         </Tooltip>
//         <span>{` ${total.toLocaleString()}`}명</span>
//       </div>

//       <ChartWrapper>
//         <ResponsiveContainer width="100%" height="100%">
//           <PieChart>
//             <Pie
//               dataKey="avg"
//               data={datasource}
//               innerRadius={60}
//               outerRadius={80}
//             >
//               {datasource?.map((entry, index) => {
//                 return (
//                   <Cell key={`cell-${index}`} fill={COLORS_DIC[entry.type]} />
//                 );
//               })}
//             </Pie>
//             <RechartsTooltip content={<UserDashboardChartTooltip />} />
//             <Legend
//               content={
//                 <UserChartLegend legendOptions={USER_CHART_LEGEND_AUTH} />
//               }
//             />
//           </PieChart>
//         </ResponsiveContainer>
//       </ChartWrapper>
//     </StyledWrapper>
//   );
// };

// export default UserAuthChart;

// // 상단 툴팁 컴포넌트
// const CutomTooltip = () => {
//   const strongStyle = {
//     display: 'inline-block',
//     fontWeight: 'bold',
//   };

//   return (
//     <div>
//       <strong style={strongStyle}>계(SNS 가입 회원 수)</strong>
//       <div className="tooltip-content">
//         탈퇴 회원은 제외합니다.
//         <br />
//         휴면 회원은 포함합니다.
//       </div>
//     </div>
//   );
// };

// // 차트 툴팁 컴포넌트
// const UserDashboardChartTooltip = (payload) => {
//   const { payload: tooltipValue } = payload;
//   const type = tooltipValue[0]?.payload?.payload?.type;
//   const avg = tooltipValue[0]?.payload?.payload?.avg;
//   const count = tooltipValue[0]?.payload?.payload?.count;

//   if (!payload) return null;

//   return (
//     <CustomTooltipWrapper>
//       <TooltipItem color={COLORS_DIC[type]}>
//         <div className="label">{type}</div>
//         <strong className="value">{`${avg}% (${count}명)`}</strong>
//       </TooltipItem>
//     </CustomTooltipWrapper>
//   );
// };

// const StyledWrapper = styled.div`
//   width: 100%;
// `;

// // 차트를 감싼 styled 컴포넌트
// const ChartWrapper = styled.div`
//   display: flex;
//   align-items: center;
//   justify-content: center;
//   width: 100%;
//   height: 260px;
// `;

// // 차트 툴팁 styled 컴포넌트
// const CustomTooltipWrapper = styled.div`
//   padding: 16px;
//   background-color: #fff;
//   border-radius: 4px;
//   box-shadow: 2px 3px 8px #00000022;
// `;

// //차트 툴팁 payload item 컴포넌트
// const TooltipItem = styled.div`
//   display: flex;
//   justify-content: left;
//   position: relative;
//   text-transform: uppercase;

//   &::before {
//     content: '';
//     position: absolute;
//     left: 0;
//     top: 50%;
//     transform: translateY(-50%);
//     width: 8px;
//     height: 8px;
//     border-radius: 50%;
//     background-color: ${(props) => `${props.color}`};
//   }

//   .label {
//     margin: 0 16px;
//   }

//   .value {
//     font-weight: bold;
//   }
// `;