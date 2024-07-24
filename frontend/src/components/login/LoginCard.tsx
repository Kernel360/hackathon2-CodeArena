import {
    Card,
    CardContent,
    CardDescription,
    CardHeader,
    CardTitle,
} from "@/components/ui/card"
import LoginForm from "./LoginForm"
import { Separator } from "@/components/ui/separator"
import { ImageMain } from "../ui/image/ImageMain"

export default function LoginCard() {
    return (
        <Card className=" flex flex-col w-[450px] items-center justify-center h-screen">
            <ImageMain/>
            <CardHeader>
                {/* <Logo/> */}
                <CardTitle>로그인</CardTitle>
                <CardDescription>로그인 UI 입니다</CardDescription>
            </CardHeader>
            <Separator />
            <CardContent>
                <LoginForm />
            </CardContent>
        </Card>
    )
}
