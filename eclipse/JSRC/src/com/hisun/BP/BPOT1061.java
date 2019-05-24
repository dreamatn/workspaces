package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1061 {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String CPN_S_MAT_ACOBL = "BP-S-MATAIN-ACOBL";
    String WK_PARM_TYPE = "PDD15";
    String WK_PARM_CODE = "001";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_DAYS = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSACOB BPCSACOB = new BPCSACOB();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPRPARM BPRPARM = new BPRPARM();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    SCCGWA SCCGWA;
    BPB1061_AWA_1061 BPB1061_AWA_1061;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT1061 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1061_AWA_1061>");
        BPB1061_AWA_1061 = (BPB1061_AWA_1061) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        IBS.init(SCCGWA, BPCSACOB);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB1061_AWA_1061.AC_FLG);
        CEP.TRC(SCCGWA, BPB1061_AWA_1061.AC_TYPE);
        CEP.TRC(SCCGWA, BPB1061_AWA_1061.CI_NO);
        CEP.TRC(SCCGWA, BPB1061_AWA_1061.ACNO_SEQ);
        CEP.TRC(SCCGWA, BPB1061_AWA_1061.REMARK);
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_CREATE_ACOBL();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB1061_AWA_1061.AC_FLG == ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AC_TYPE_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPB1061_AWA_1061.AC_TYPE == ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AC_TYPE_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPB1061_AWA_1061.ACNO_SEQ == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_E_NO_AC_SEQ;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPB1061_AWA_1061.ACNO_SEQ);
        if (BPB1061_AWA_1061.ACNO_SEQ > 99999999) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_SEQ_OVRERANGE);
        }
        if (BPB1061_AWA_1061.CI_NO.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CI_MUST_INP;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_CREATE_ACOBL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSACOB);
        BPCSACOB.FUNC = 'A';
        B040_TRANS_ACOBL();
        if (pgmRtn) return;
        S000_CALL_BPZSACOB();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSACOB.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_DUPKEY)) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_EXIST);
        }
    }
    public void B040_TRANS_ACOBL() throws IOException,SQLException,Exception {
        BPCSACOB.DATA.AC_FLG = BPB1061_AWA_1061.AC_FLG;
        BPCSACOB.DATA.AC_TYPE = BPB1061_AWA_1061.AC_TYPE;
        BPCSACOB.DATA.CI_NO = BPB1061_AWA_1061.CI_NO;
        CEP.TRC(SCCGWA, BPB1061_AWA_1061.CI_NO);
        CEP.TRC(SCCGWA, BPCSACOB.DATA.CI_NO);
        BPCSACOB.DATA.AC_NO_SEQ = BPB1061_AWA_1061.ACNO_SEQ;
        BPCSACOB.DATA.REMARK = BPB1061_AWA_1061.REMARK;
        R000_GET_EXP_DT_CN();
        if (pgmRtn) return;
        BPCSACOB.DATA.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, BPCSACOB.DATA.EFF_DATE);
        BPCSACOB.DATA.EXP_DATE = BPCOCLWD.DATE2;
        CEP.TRC(SCCGWA, BPCSACOB.DATA.EXP_DATE);
        CEP.TRC(SCCGWA, BPCSACOB.DATA.AC_TYPE);
        CEP.TRC(SCCGWA, BPCSACOB.DATA.CI_NO);
        CEP.TRC(SCCGWA, BPCSACOB.DATA.AC_NO_SEQ);
        CEP.TRC(SCCGWA, BPCSACOB.DATA.REMARK);
    }
    public void R000_GET_EXP_DT_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRMBPM);
        IBS.init(SCCGWA, BPRPARM);
        BPCRMBPM.RC.RC_MMO = "BP";
        BPRPARM.KEY.TYPE = WK_PARM_TYPE;
        BPRPARM.KEY.CODE = WK_PARM_CODE;
        BPRPARM.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCRMBPM.FUNC = 'I';
        BPCRMBPM.PTR = BPRPARM;
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRMBPM.RETURN_INFO);
        if (BPCRMBPM.RETURN_INFO == 'F') {
            if (BPRPARM.BLOB_VAL == null) BPRPARM.BLOB_VAL = "";
            JIBS_tmp_int = BPRPARM.BLOB_VAL.length();
            for (int i=0;i<9500-JIBS_tmp_int;i++) BPRPARM.BLOB_VAL += " ";
            if (BPRPARM.BLOB_VAL.substring(0, 3).trim().length() == 0) WS_DAYS = 0;
            else WS_DAYS = Integer.parseInt(BPRPARM.BLOB_VAL.substring(0, 3));
            CEP.TRC(SCCGWA, WS_DAYS);
        }
        if (WS_DAYS == 0) {
            if (BPB1061_AWA_1061.DAYS != 0) {
                WS_DAYS = BPB1061_AWA_1061.DAYS;
            } else {
                WS_DAYS = 30;
            }
        }
        CEP.TRC(SCCGWA, WS_DAYS);
        IBS.init(SCCGWA, BPCOCLWD);
        BPCOCLWD.CAL_CODE = BPCRBANK.CALD_BUI;
        CEP.TRC(SCCGWA, BPCOCLWD.CAL_CODE);
        BPCOCLWD.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, BPCOCLWD.DATE1);
        BPCOCLWD.DAYS = WS_DAYS;
        CEP.TRC(SCCGWA, BPCOCLWD.DAYS);
        CEP.TRC(SCCGWA, BPCOCLWD.DATE1);
        CEP.TRC(SCCGWA, BPCOCLWD.DAYS);
        CEP.TRC(SCCGWA, BPCOCLWD.CAL_CODE);
        S000_CALL_BPZPCLWD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCOCLWD.DATE2);
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
        CEP.TRC(SCCGWA, BPCOCLWD.RC);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOCLWD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSACOB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_MAT_ACOBL, BPCSACOB);
        CEP.TRC(SCCGWA, BPCSACOB.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSACOB.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSACOB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRMBPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MBRW-PARM", BPCRMBPM);
        CEP.TRC(SCCGWA, BPCRMBPM.RC);
        if (BPCRMBPM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRMBPM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
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
