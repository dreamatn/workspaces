

import java.util.ArrayList;
import java.util.List;

import com.hisun.SC.OUTFMT;


public class TCCTOE {
	public SYS_HEADO SYS_HEAD = new SYS_HEADO();
	public APP_HEADO APP_HEAD = new APP_HEADO();
	public List<OUTFMT> outfmt = new ArrayList<OUTFMT>();

	public SYS_HEADO getSYS_HEAD() {
        return SYS_HEAD;
    }

	public APP_HEADO getAPP_HEAD() {
        return APP_HEAD;
    }

}
