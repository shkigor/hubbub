package com.grailsinaction

class PhotoUploadCommand {
    byte[] photo
    String loginId
}

class ImageController {

    def upload(PhotoUploadCommand puc) {
        def user = User.findByLoginId(puc.loginId)
        user.profile.photo = puc.photo
        user.save(flush: true)
        redirect controller: "user", action: "profile", id: puc.loginId
    }

//    def rawUpload() {
//        // a Spring MultipartFile
//        def mpf = request.getFile('photo')
//        if (!mpf?.empty && mpf.size < 1024 * 200) {
//            mpf.transferTo(new File(
//                    "/hubbub/images/${params.loginId}/mugshot.gif"))
//        }
//    }

    def form() {
        // pass through to upload form
        [userList: User.list()]
    }

    def renderImage(String id) {
        def user = User.findByLoginId(id)
        if (user?.profile?.photo) {
            response.setContentLength(user.profile.photo.size())
            response.outputStream.write(user.profile.photo)
        } else {
            response.sendError(404)
        }
    }
}