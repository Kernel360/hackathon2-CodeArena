import {
    Card,
    CardContent,
    CardDescription,
    CardHeader,
    CardTitle,
} from "@/components/ui/card"
import SignUpForm from "./SignUpForm"
import { Separator } from "@/components/ui/separator"
import { ImageMain } from "../ui/image/ImageMain"

export default function SignUpCard() {
    return (
        <Card className=" flex flex-col w-[450px] items-center justify-center h-screen">
            <ImageMain/>
            <CardHeader>
                <CardTitle>회원 가입</CardTitle>
                <CardDescription>회원 가입 UI 입니다</CardDescription>
            </CardHeader>
            <Separator />
            <CardContent>
                <SignUpForm />
            </CardContent>
        </Card>
    )
}
