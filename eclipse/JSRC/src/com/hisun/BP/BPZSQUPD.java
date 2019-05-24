package com.hisun.BP;

import com.hisun.AI.*;
import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSQUPD {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String K_CMP_MAIN_BPTEXUPF = "BP-R-MAINT-UPDATA";
    String K_FMT_CD = "BPX01";
    String WS_ERR_MSG = " ";
    int WS_REC_LEN = 0;
    BPZSQUPD_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPZSQUPD_WS_OUTPUT_DATA();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPREXUPF BPREXUPF = new BPREXUPF();
    BPCOEXRT BPCOEXRT = new BPCOEXRT();
    BPCOEHOL BPCOEHOL = new BPCOEHOL();
    BPCOERTE BPCOERTE = new BPCOERTE();
    BPCECNGL BPCECNGL = new BPCECNGL();
    AICOODEL AICOODEL = new AICOODEL();
    BPCRMUPD BPCRMUPD = new BPCRMUPD();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    BPCSQUPD BPCSQUPD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSQUPD BPCSQUPD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSQUPD = BPCSQUPD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSQUPD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPCSQUPD.FUNC == 'Q') {
            B010_QUERY_EXUPF_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCSQUPD.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSQUPD.KEY);
        if (JIBS_tmp_str[0].trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BATCH_NO_EMPTY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSQUPD.TYPE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UPLOAD_TYPE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_EXUPF_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPREXUPF);
        IBS.init(SCCGWA, BPCRMUPD);
        BPCRMUPD.INFO.POINTER = BPREXUPF;
        BPCRMUPD.INFO.LEN = 183;
        BPCRMUPD.INFO.FUNC = 'Q';
        BPREXUPF.KEY.BATCH_NO = BPCSQUPD.KEY.BATCH_NO;
        BPREXUPF.KEY.SEQ = BPCSQUPD.KEY.SEQ;
        CEP.TRC(SCCGWA, BPCSQUPD.KEY.BATCH_NO);
        CEP.TRC(SCCGWA, BPCSQUPD.KEY.SEQ);
        CEP.TRC(SCCGWA, BPCSQUPD.TYPE);
        S000_CALL_BPZRMUPD();
        if (pgmRtn) return;
        R000_OUTPUT_DATA_PROCESS();
        if (pgmRtn) return;
    }
    public void R000_OUTPUT_DATA_PROCESS() throws IOException,SQLException,Exception {
        if (BPCSQUPD.TYPE.equalsIgnoreCase("01")) {
            IBS.init(SCCGWA, BPCOEXRT);
            BPCOEXRT.KEY.BATCN_NO = BPREXUPF.KEY.BATCH_NO;
            BPCOEXRT.KEY.SEQ_NO = BPREXUPF.KEY.SEQ;
            WS_REC_LEN = 65;
            if (BPREXUPF.RECORD == null) BPREXUPF.RECORD = "";
            JIBS_tmp_int = BPREXUPF.RECORD.length();
            for (int i=0;i<1000-JIBS_tmp_int;i++) BPREXUPF.RECORD += " ";
            IBS.CPY2CLS(SCCGWA, BPREXUPF.RECORD.substring(0, WS_REC_LEN), BPCOEXRT.DATA);
            IBS.init(SCCGWA, SCCFMT);
            SCCFMT.FMTID = K_FMT_CD;
            SCCFMT.DATA_PTR = BPCOEXRT;
            SCCFMT.DATA_LEN = 110;
            IBS.FMT(SCCGWA, SCCFMT);
        } else if (BPCSQUPD.TYPE.equalsIgnoreCase("02")) {
            IBS.init(SCCGWA, BPCOEHOL);
            BPCOEHOL.KEY.BATCN_NO = BPREXUPF.KEY.BATCH_NO;
            BPCOEHOL.KEY.SEQ_NO = BPREXUPF.KEY.SEQ;
            WS_REC_LEN = 19;
            if (BPREXUPF.RECORD == null) BPREXUPF.RECORD = "";
            JIBS_tmp_int = BPREXUPF.RECORD.length();
            for (int i=0;i<1000-JIBS_tmp_int;i++) BPREXUPF.RECORD += " ";
            IBS.CPY2CLS(SCCGWA, BPREXUPF.RECORD.substring(0, WS_REC_LEN), BPCOEHOL.DATA);
            IBS.init(SCCGWA, SCCFMT);
            SCCFMT.FMTID = K_FMT_CD;
            SCCFMT.DATA_PTR = BPCOEHOL;
            SCCFMT.DATA_LEN = 9239;
            IBS.FMT(SCCGWA, SCCFMT);
        } else if (BPCSQUPD.TYPE.equalsIgnoreCase("03")) {
            IBS.init(SCCGWA, AICOODEL);
            AICOODEL.KEY.BATCN_NO = BPREXUPF.KEY.BATCH_NO;
            AICOODEL.KEY.SEQ_NO = BPREXUPF.KEY.SEQ;
            AICOODEL.KEY.EXEC_TYPE = "03";
            WS_REC_LEN = 1215;
            if (BPREXUPF.RECORD == null) BPREXUPF.RECORD = "";
            JIBS_tmp_int = BPREXUPF.RECORD.length();
            for (int i=0;i<1000-JIBS_tmp_int;i++) BPREXUPF.RECORD += " ";
            IBS.CPY2CLS(SCCGWA, BPREXUPF.RECORD.substring(0, WS_REC_LEN), AICOODEL.DATA);
            IBS.init(SCCGWA, SCCFMT);
            SCCFMT.FMTID = K_FMT_CD;
            SCCFMT.DATA_PTR = AICOODEL;
            SCCFMT.DATA_LEN = 1262;
            IBS.FMT(SCCGWA, SCCFMT);
        } else if (BPCSQUPD.TYPE.equalsIgnoreCase("04")) {
            IBS.init(SCCGWA, BPCECNGL);
            BPCECNGL.KEY.BATCN_NO = BPREXUPF.KEY.BATCH_NO;
            BPCECNGL.KEY.SEQ_NO = BPREXUPF.KEY.SEQ;
            WS_REC_LEN = 34;
            if (BPREXUPF.RECORD == null) BPREXUPF.RECORD = "";
            JIBS_tmp_int = BPREXUPF.RECORD.length();
            for (int i=0;i<1000-JIBS_tmp_int;i++) BPREXUPF.RECORD += " ";
            IBS.CPY2CLS(SCCGWA, BPREXUPF.RECORD.substring(0, WS_REC_LEN), BPCECNGL.DAT);
            CEP.TRC(SCCGWA, BPCECNGL);
            IBS.init(SCCGWA, SCCFMT);
            SCCFMT.FMTID = K_FMT_CD;
            SCCFMT.DATA_PTR = BPCECNGL;
            SCCFMT.DATA_LEN = 79;
            IBS.FMT(SCCGWA, SCCFMT);
        } else if (BPCSQUPD.TYPE.equalsIgnoreCase("05")) {
            IBS.init(SCCGWA, BPCOEHOL);
            BPCOERTE.KEY.BATCN_NO = BPREXUPF.KEY.BATCH_NO;
            BPCOERTE.KEY.SEQ_NO = BPREXUPF.KEY.SEQ;
            WS_REC_LEN = 40;
            if (BPREXUPF.RECORD == null) BPREXUPF.RECORD = "";
            JIBS_tmp_int = BPREXUPF.RECORD.length();
            for (int i=0;i<1000-JIBS_tmp_int;i++) BPREXUPF.RECORD += " ";
            IBS.CPY2CLS(SCCGWA, BPREXUPF.RECORD.substring(0, WS_REC_LEN), BPCOERTE.DATA);
            IBS.init(SCCGWA, SCCFMT);
            SCCFMT.FMTID = K_FMT_CD;
            SCCFMT.DATA_PTR = BPCOERTE;
            SCCFMT.DATA_LEN = 85;
            IBS.FMT(SCCGWA, SCCFMT);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID TYPE(" + BPCSQUPD.TYPE + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void S000_CALL_BPZRMUPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_MAIN_BPTEXUPF, BPCRMUPD);
        if (BPCRMUPD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRMUPD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
