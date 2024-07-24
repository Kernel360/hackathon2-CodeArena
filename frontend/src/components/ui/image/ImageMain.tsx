import Logo from "@/../public/assets/logo.png";

export const ImageMain = () => {
    return (
        <img src={Logo} className="w-[200px] rounded-sm shadow-lg hover:shadow-2xl cursor-pointer" onClick={() => window.location.href ="/"}></img>
    )
}