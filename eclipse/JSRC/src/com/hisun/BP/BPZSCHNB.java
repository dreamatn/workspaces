package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DB.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSCHNB {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_TYPE_CHNTB = "CHNTB";
    String K_OUTPUT_FMT_X = "BPP40";
    BPZSCHNB_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZSCHNB_WS_TEMP_VARIABLE();
    short WS_CNT_CHNTB = 0;
    short WS_CNT_OUTPUT = 0;
    BPZSCHNB_WS_CHNTB_INFO WS_CHNTB_INFO = new BPZSCHNB_WS_CHNTB_INFO();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPRPARM BPRPARM = new BPRPARM();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BPRCHNTB BPRCHNTB = new BPRCHNTB();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGCPT SCCGCPT;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSCHNB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRMBPM);
        IBS.init(SCCGWA, BPRPARM);
        BPCRMBPM.RC.RC_MMO = "BP";
        B100_STARTBR_PARM();
        if (pgmRtn) return;
        B200_READNEXT_PARM();
        if (pgmRtn) return;
        WS_CNT_OUTPUT = 0;
        for (WS_CNT_CHNTB = 1; BPCRMBPM.RETURN_INFO != 'L' 
            && WS_CNT_CHNTB <= 100; WS_CNT_CHNTB += 1) {
            CEP.TRC(SCCGWA, WS_CNT_CHNTB);
            CEP.TRC(SCCGWA, BPRPARM.KEY.CODE);
            CEP.TRC(SCCGWA, BPCRMBPM.RETURN_INFO);
            IBS.init(SCCGWA, BPRCHNTB);
            IBS.CPY2CLS(SCCGWA, BPRPARM.BLOB_VAL, BPRCHNTB.DATA_TXT);
            CEP.TRC(SCCGWA, BPRCHNTB.DATA_TXT);
            if (BPRCHNTB.DATA_TXT.INNER_FLG == 'N') {
                WS_CNT_OUTPUT = (short) (WS_CNT_OUTPUT + 1);
                if (BPRPARM.KEY.CODE == null) BPRPARM.KEY.CODE = "";
                JIBS_tmp_int = BPRPARM.KEY.CODE.length();
                for (int i=0;i<40-JIBS_tmp_int;i++) BPRPARM.KEY.CODE += " ";
                WS_CHNTB_INFO.WS_CHNTB_DATA[WS_CNT_OUTPUT-1].WS_CHNTB_NO = BPRPARM.KEY.CODE.substring(0, 5);
            }
            B200_READNEXT_PARM();
            if (pgmRtn) return;
        }
        B300_ENDBR_PARM();
        if (pgmRtn) return;
        B400_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_STARTBR_PARM() throws IOException,SQLException,Exception {
        BPCRMBPM.FUNC = 'S';
        BPRPARM.KEY.TYPE = K_TYPE_CHNTB;
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
    }
    public void B200_READNEXT_PARM() throws IOException,SQLException,Exception {
        BPCRMBPM.FUNC = 'R';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
    }
    public void B300_ENDBR_PARM() throws IOException,SQLException,Exception {
        BPCRMBPM.FUNC = 'E';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
    }
    public void B400_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_X;
        SCCFMT.DATA_PTR = WS_CHNTB_INFO;
        SCCFMT.DATA_LEN = 6600;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, WS_CHNTB_INFO);
    }
    public void S000_CALL_BPZRMBPM() throws IOException,SQLException,Exception {
        BPCRMBPM.PTR = BPRPARM;
        IBS.CALLCPN(SCCGWA, "BP-R-MBRW-PARM", BPCRMBPM);
        if (BPCRMBPM.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCRMBPM.RC);
            CEP.ERR(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
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
