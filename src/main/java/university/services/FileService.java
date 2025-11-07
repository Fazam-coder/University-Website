package university.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import university.util.CloudinaryUtil;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class FileService {
    public static final Cloudinary cloudinary = CloudinaryUtil.getInstance();

    public static String saveAndGetPathname(Part part) throws IOException {
        if (part == null || part.getSize() == 0) {
            return null;
        }

        try (InputStream is = part.getInputStream()) {
            byte[] bytes = is.readAllBytes();
            Map<String, Object> options = new HashMap<>();
            options.put("folder", "avatars");
            options.put("resource_type", "auto");

            return cloudinary.uploader().upload(bytes, options).get("secure_url").toString();
        }
    }
}
