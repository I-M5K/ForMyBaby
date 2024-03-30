// import React from "react";
// import { Dialog, DialogTrigger, DialogContent, DialogHeader, DialogFooter, DialogTitle, DialogDescription, DialogClose } from "@radix-ui/react-dialog";
// import { cn } from "@/lib/utils";

// const DialogPortal = ({ className, ...props }) => (
//     <Dialog.Portal className={cn(className)} {...props} />
// );

// const DialogOverlay = React.forwardRef((props, ref) => (
//     <Dialog.Overlay
//         ref={ref}
//         className={cn(
//             "fixed inset-0 z-50 bg-white/80 backdrop-blur-sm data-[state=open]:animate-in data-[state=closed]:animate-out data-[state=closed]:fade-out-0 data-[state=open]:fade-in-0 dark:bg-neutral-950/80",
//             props.className
//         )}
//         {...props}
//     />
// ));

// const DialogContentWrapper = ({ className, children, ...props }) => (
//     <DialogPortal>
//         <DialogOverlay />
//         <DialogContent
//             className={cn(
//                 "fixed left-[50%] top-[50%] z-50 grid w-full max-w-lg translate-x-[-50%] translate-y-[-50%] gap-4 border border-neutral-200 bg-white p-6 shadow-lg duration-200 data-[state=open]:animate-in data-[state=closed]:animate-out data-[state=closed]:fade-out-0 data-[state=open]:fade-in-0 data-[state=closed]:zoom-out-95 data-[state=open]:zoom-in-95 data-[state=closed]:slide-out-to-left-1/2 data-[state=closed]:slide-out-to-top-[48%] data-[state=open]:slide-in-from-left-1/2 data-[state=open]:slide-in-from-top-[48%] sm:rounded-lg md:w-full dark:border-neutral-800 dark:bg-neutral-950",
//                 className
//             )}
//             {...props}>
//             {children}
//         </DialogContent>
//     </DialogPortal>
// );

// const DialogHeaderWrapper = ({ className, ...props }) => (
//     <DialogHeader
//         className={cn(
//             "flex flex-col space-y-1.5 text-center sm:text-left",
//             className
//         )}
//         {...props}
//     />
// );

// const DialogFooterWrapper = ({ className, ...props }) => (
//     <DialogFooter
//         className={cn(
//             "flex flex-col-reverse sm:flex-row sm:justify-end sm:space-x-2",
//             className
//         )}
//         {...props}
//     />
// );

// const DialogTitleWrapper = React.forwardRef((props, ref) => (
//     <DialogTitle
//         ref={ref}
//         className={cn(
//             "text-lg font-semibold leading-none tracking-tight",
//             props.className
//         )}
//         {...props}
//     />
// ));

// const DialogDescriptionWrapper = React.forwardRef((props, ref) => (
//     <DialogDescription
//         ref={ref}
//         className={cn("text-sm text-neutral-500 dark:text-neutral-400", props.className)}
//         {...props}
//     />
// ));

// export {
//     Dialog,
//     DialogTrigger,
//     DialogContentWrapper as DialogContent,
//     DialogHeaderWrapper as DialogHeader,
//     DialogFooterWrapper as DialogFooter,
//     DialogTitleWrapper as DialogTitle,
//     DialogDescriptionWrapper as DialogDescription,
//     DialogClose
// };
