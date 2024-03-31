// import React, { useEffect, useState } from "react";
// import {
//     Dialog,
//     DialogContent,
//     DialogFooter,
//     DialogHeader,
//     DialogTitle,
// } from "../dialog";
// import { useFCM } from "@/config/useFcm";
// //import { Button } from "../ui/button";

// export const PermissionPushModal = () => {
//     const [isOpen, setIsOpen] = useState(false);

//     const { loadToken } = useFCM();

//     useEffect(() => {
//         if ("Notification" in window && Notification.permission !== "granted")
//             setIsOpen(true);
//     }, []);

//     const handleSubmit = () => {
//         loadToken();
//         setIsOpen(false);
//     };

//     return (
//         <Dialog open={isOpen}>
//             <DialogContent>
//                 <DialogHeader>
//                     <DialogTitle>Need Push Notifications permission!</DialogTitle>
//                 </DialogHeader>
//                 <DialogFooter>
//                     <button type="button" onClick={handleSubmit}>
//                         Yes
//                     </button>
//                     <button type="button" variant="outline" onClick={handleSubmit}>
//                         No
//                     </button>
//                 </DialogFooter>
//             </DialogContent>
//         </Dialog>
//     );
// };
