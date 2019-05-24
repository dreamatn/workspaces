package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DB.*;
import com.hisun.SM.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSBPRM {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    short K_Q_MAX_CNT = 200;
    BPZSBPRM_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZSBPRM_WS_TEMP_VARIABLE();
    short WS_MAX_COL_NO = 0;
    short WS_TS_CNT = 0;
    String WS_TS_REC = " ";
    short WS_LEN = 0;
    BPZSBPRM_WS_TS_CNTL WS_TS_CNTL = new BPZSBPRM_WS_TS_CNTL();
    BPZSBPRM_WS_SPE_OUT WS_SPE_OUT = new BPZSBPRM_WS_SPE_OUT();
    BPZSBPRM_WS_OUT_DATA WS_OUT_DATA = new BPZSBPRM_WS_OUT_DATA();
    char WS_NEXT_FLG = ' ';
    char WS_CURRENT_FLG = ' ';
    char WS_EOF_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    BPCRMPRP BPCRMPRP = new BPCRMPRP();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPRPARP BPRPARP = new BPRPARP();
    BPRPARM BPRPARM = new BPRPARM();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCTLM_MSG SCCCTLM_MSG = new SCCCTLM_MSG();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGCPT SCCGCPT;
    BPRTRT SMRTRTT;
    BPCSBPRM BPCSBPRM;
    public void MP(SCCGWA SCCGWA, BPCSBPRM BPCSBPRM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBPRM = BPCSBPRM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSBPRM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRMPRP);
        BPCRMPRP.RC.RC_MMO = "BP";
        BPCRMPRP.PTR = BPRPARP;
        IBS.init(SCCGWA, BPCRMBPM);
        BPCRMBPM.RC.RC_MMO = "BP";
        BPCRMBPM.PTR = BPRPARM;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
        if (BPCSBPRM.NUM != 0) {
            K_Q_MAX_CNT = BPCSBPRM.NUM;
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_READ_PARP();
        if (pgmRtn) return;
        B005_READY_TS_HEAD_TIT();
        if (pgmRtn) return;
        B010_STARTBR_PARM();
        if (pgmRtn) return;
        B015_READNEXT_PARM();
        if (pgmRtn) return;
        WS_NEXT_FLG = 'N';
        WS_TEMP_VARIABLE.WS_PARM_CODE = BPRPARM.KEY.CODE;
        while (BPCRMBPM.RETURN_INFO != 'L' 
            && WS_EOF_FLG != 'Y' 
            && SCCMPAG.FUNC != 'E') {
            if (!BPRPARM.KEY.CODE.equalsIgnoreCase(WS_TEMP_VARIABLE.WS_PARM_CODE)) {
                WS_NEXT_FLG = 'N';
                WS_TEMP_VARIABLE.WS_PARM_CODE = BPRPARM.KEY.CODE;
            }
            CEP.TRC(SCCGWA, BPRPARM.KEY.CODE);
            CEP.TRC(SCCGWA, WS_NEXT_FLG);
            if (WS_NEXT_FLG == 'N') {
                if (BPRPARM.EFF_DATE > SCCGWA.COMM_AREA.AC_DATE) {
                    WS_CURRENT_FLG = 'N';
                } else {
                    if (BPRPARM.EXP_DATE > SCCGWA.COMM_AREA.AC_DATE) {
                        WS_CURRENT_FLG = 'Y';
                    } else {
                        WS_CURRENT_FLG = 'D';
                    }
                    WS_NEXT_FLG = 'Y';
                }
                B025_OUT_RECORD();
                if (pgmRtn) return;
            }
            B015_READNEXT_PARM();
            if (pgmRtn) return;
        }
        B020_ENDBR_PARM();
        if (pgmRtn) return;
    }
    public void B001_READ_PARP() throws IOException,SQLException,Exception {
        BPCRMPRP.FUNC = 'Q';
        BPRPARP.KEY.TYPE = BPCSBPRM.TYPE;
        S000_CALL_BPZRMPRP();
        if (pgmRtn) return;
        if (BPCRMPRP.RETURN_INFO == 'N') {
            WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_TYPE_NOT_EXIT;
            CEP.ERR(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        }
    }
    public void B005_READY_TS_HEAD_TIT() throws IOException,SQLException,Exception {
        WS_MAX_COL_NO = 66;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = WS_TS_CNTL.WS_TS_MAIN_TIT;
        SCCMPAG.MAX_COL_NO = 138;
        SCCMPAG.SUBT_ROW_CNT = WS_TS_CNTL.WS_TS_TIT_NUM;
        SCCMPAG.SCR_ROW_CNT = 50;
        B_MPAG();
        if (pgmRtn) return;
        WS_TS_CNT = 0;
        WS_SPE_OUT.WS_OUT_TYPE = BPRPARP.KEY.TYPE;
        WS_SPE_OUT.WS_OUT_TXCODE = BPRPARP.TXN_ID;
        CEP.TRC(SCCGWA, BPRPARP.KEY.TYPE);
        CEP.TRC(SCCGWA, BPRPARP.TXN_ID);
        CEP.TRC(SCCGWA, WS_SPE_OUT.WS_OUT_TXCODE);
        WS_TS_REC = IBS.CLS2CPY(SCCGWA, WS_SPE_OUT);
        WS_LEN = 13;
        S000_WRITE_TS();
        if (pgmRtn) return;
    }
    public void B010_STARTBR_PARM() throws IOException,SQLException,Exception {
        BPCRMBPM.FUNC = 'S';
        BPRPARM.KEY.TYPE = BPCSBPRM.TYPE;
        BPRPARM.KEY.CODE = BPCSBPRM.CODE;
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
    }
    public void B015_READNEXT_PARM() throws IOException,SQLException,Exception {
        BPCRMBPM.FUNC = 'R';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
    }
    public void B020_ENDBR_PARM() throws IOException,SQLException,Exception {
        BPCRMBPM.FUNC = 'E';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
    }
    public void B025_OUT_RECORD() throws IOException,SQLException,Exception {
        WS_OUT_DATA.WS_OUT_CODE = BPRPARM.KEY.CODE;
        WS_OUT_DATA.WS_OUT_CURRENT = WS_CURRENT_FLG;
        WS_OUT_DATA.WS_OUT_EFF_DATE = "" + BPRPARM.EFF_DATE;
        JIBS_tmp_int = WS_OUT_DATA.WS_OUT_EFF_DATE.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) WS_OUT_DATA.WS_OUT_EFF_DATE = "0" + WS_OUT_DATA.WS_OUT_EFF_DATE;
        WS_OUT_DATA.WS_OUT_EXP_DATE = "" + BPRPARM.EXP_DATE;
        JIBS_tmp_int = WS_OUT_DATA.WS_OUT_EXP_DATE.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) WS_OUT_DATA.WS_OUT_EXP_DATE = "0" + WS_OUT_DATA.WS_OUT_EXP_DATE;
        WS_OUT_DATA.WS_OUT_DESC = BPRPARM.DESC;
        WS_OUT_DATA.WS_OUT_CDESC = BPRPARM.CDESC;
        WS_TS_REC = " ";
        WS_TS_REC = IBS.CLS2CPY(SCCGWA, WS_OUT_DATA);
        CEP.TRC(SCCGWA, WS_TS_REC);
        WS_LEN = 138;
        WS_TS_CNT += 1;
        if (SCCMPAG.FUNC != 'E') {
            S000_WRITE_TS();
            if (pgmRtn) return;
        } else {
            WS_TS_REC = " ";
            WS_TS_REC = "TO BE CONTINUED";
            WS_LEN = 138;
            S000_WRITE_TS();
            if (pgmRtn) return;
            WS_EOF_FLG = 'Y';
        }
    }
    public void S000_WRITE_TS() throws IOException,SQLException,Exception {
        if (SCCMPAG.FUNC == 'E') {
            WS_TEMP_VARIABLE.WS_MSGID = SCCCTLM_MSG.SC_ERR_ROW_LIMIT;
            CEP.ERR(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        }
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = WS_TS_REC;
        SCCMPAG.DATA_LEN = WS_LEN;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRMPRP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MAINT-PRP", BPCRMPRP);
        if (BPCRMPRP.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCRMPRP.RC);
            CEP.ERR(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        }
    }
    public void S000_CALL_BPZRMBPM() throws IOException,SQLException,Exception {
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
