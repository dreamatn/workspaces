package com.hisun.BP;

import com.hisun.AI.*;
import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZGCNGL {
    int JIBS_tmp_int;
    DBParm BPTCNGL_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int WS_I = 0;
    int WS_J = 0;
    String WS_MSG_ERR = " ";
    short WS_FLD_NO = 0;
    char WS_FND_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRCNGL BPRCNGL = new BPRCNGL();
    SCCGWA SCCGWA;
    BPCGCNGL BPCGCNGL;
    public void MP(SCCGWA SCCGWA, BPCGCNGL BPCGCNGL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCGCNGL = BPCGCNGL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZGCNGL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCGCNGL.FOUND_FLG = ' ';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPCGCNGL.FUNC == 'I') {
            B020_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_IPT_FUNC_ERR);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCGCNGL.FUNC);
        CEP.TRC(SCCGWA, BPCGCNGL.PRDT_CODE);
        CEP.TRC(SCCGWA, BPCGCNGL.FOUND_FLG);
        if (BPCGCNGL.PRDT_CODE.trim().length() == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_PRD_CODE_MUSTINPUT);
        }
    }
    public void B020_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCNGL);
        BPRCNGL.KEY.OTH = "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%";
        CEP.TRC(SCCGWA, BPRCNGL.KEY.OTH);
        if (BPRCNGL.KEY.OTH == null) BPRCNGL.KEY.OTH = "";
        JIBS_tmp_int = BPRCNGL.KEY.OTH.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) BPRCNGL.KEY.OTH += " ";
        if (BPCGCNGL.PRDT_CODE == null) BPCGCNGL.PRDT_CODE = "";
        JIBS_tmp_int = BPCGCNGL.PRDT_CODE.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPCGCNGL.PRDT_CODE += " ";
        BPRCNGL.KEY.OTH = BPCGCNGL.PRDT_CODE + BPRCNGL.KEY.OTH.substring(10);
        CEP.TRC(SCCGWA, BPRCNGL.KEY.OTH);
        T000_READ_BPTCNGL_FIRST();
        if (pgmRtn) return;
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ERR);
        Z_RET();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTCNGL_FIRST() throws IOException,SQLException,Exception {
        BPTCNGL_RD = new DBParm();
        BPTCNGL_RD.TableName = "BPTCNGL";
        BPTCNGL_RD.where = "OTH LIKE :BPRCNGL.KEY.OTH";
        BPTCNGL_RD.fst = true;
        IBS.READ(SCCGWA, BPRCNGL, this, BPTCNGL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, BPCGCNGL.FOUND_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCGCNGL.FOUND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCGCNGL.FOUND_FLG = 'N';
        } else {
        }
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
