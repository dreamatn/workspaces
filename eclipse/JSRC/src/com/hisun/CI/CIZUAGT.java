package com.hisun.CI;

import com.hisun.BP.*;
import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZUAGT {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm CITAGT_RD;
    boolean pgmRtn = false;
    String K_SEQ_TYPE = "CIAGT";
    String WS_MSGID = " ";
    String WS_ERR_INFO = " ";
    char WS_AGT_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CIRAGT CIRAGT = new CIRAGT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPRSEQ BPRSEQ = new BPRSEQ();
    BPCRMSEQ BPCRMSEQ = new BPCRMSEQ();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICUAGT CICUAGT;
    public void MP(SCCGWA SCCGWA, CICUAGT CICUAGT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICUAGT = CICUAGT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZUAGT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CICUAGT.RC.RC_MMO = "CI";
        CICUAGT.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_NEW_ORDNO_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, BPRPRMT);
        if (CICUAGT.DATA.AGT_TYP.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AGT_TYP_MUST_INPUT, CICUAGT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CICUAGT.DATA.AGT_TYP);
        CEP.TRC(SCCGWA, K_SEQ_TYPE);
        BPRPRMT.KEY.TYP = K_SEQ_TYPE;
        BPRPRMT.KEY.CD = CICUAGT.DATA.AGT_TYP;
        BPCPRMR.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
    }
    public void B010_GET_OLD_ORDNO_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "A");
        CEP.TRC(SCCGWA, "2");
        IBS.init(SCCGWA, CIRAGT);
        CEP.TRC(SCCGWA, "B");
        CEP.TRC(SCCGWA, CICUAGT.DATA.AGT_TYP);
        CEP.TRC(SCCGWA, CICUAGT.DATA.AGT_TYP);
        CEP.TRC(SCCGWA, CICUAGT.DATA.ENTY_TYP);
        CEP.TRC(SCCGWA, CICUAGT.DATA.ENTY_TYP);
        CEP.TRC(SCCGWA, CICUAGT.DATA.ENTY_NO);
        CEP.TRC(SCCGWA, CICUAGT.DATA.ENTY_NO);
        CIRAGT.AGT_TYP = CICUAGT.DATA.AGT_TYP;
        CIRAGT.KEY.ENTY_TYP = CICUAGT.DATA.ENTY_TYP;
        CIRAGT.KEY.ENTY_NO = CICUAGT.DATA.ENTY_NO;
        T000_READ_CITAGT_INDEX();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CIRAGT.KEY.AGT_NO);
    }
    public void B020_GET_NEW_ORDNO_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "C");
        IBS.init(SCCGWA, BPCSGSEQ);
        CEP.TRC(SCCGWA, "D");
        CEP.TRC(SCCGWA, K_SEQ_TYPE);
        BPCSGSEQ.TYPE = K_SEQ_TYPE;
        CEP.TRC(SCCGWA, CICUAGT.DATA.AGT_TYP);
        if (CICUAGT.DATA.AGT_TYP == null) CICUAGT.DATA.AGT_TYP = "";
        JIBS_tmp_int = CICUAGT.DATA.AGT_TYP.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) CICUAGT.DATA.AGT_TYP += " ";
        BPCSGSEQ.CODE = CICUAGT.DATA.AGT_TYP.substring(0, 7);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        S000_CALL_BPZSGSEQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSGSEQ.RC);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                IBS.init(SCCGWA, BPRSEQ);
                BPRSEQ.KEY.TYPE = K_SEQ_TYPE;
                if (CICUAGT.DATA.AGT_TYP == null) CICUAGT.DATA.AGT_TYP = "";
                JIBS_tmp_int = CICUAGT.DATA.AGT_TYP.length();
                for (int i=0;i<12-JIBS_tmp_int;i++) CICUAGT.DATA.AGT_TYP += " ";
                BPRSEQ.KEY.NAME = CICUAGT.DATA.AGT_TYP.substring(0, 7);
                BPRSEQ.SEQ_NO = 1;
                BPRSEQ.FIRST_NUM = 1;
                BPRSEQ.INIT_ZERO = 'N';
                BPRSEQ.SKIP_FLG = 'N';
                BPRSEQ.OBL_FLG = 'N';
                BPRSEQ.STEP_NUM = 1;
                BPRSEQ.MAX_SEQ_NO = 999999999999999;
                BPRSEQ.MAX_FLG = 'E';
                BPRSEQ.WARN_SEQ_NO = 900000000000000;
                IBS.init(SCCGWA, BPCRMSEQ);
                BPCRMSEQ.FUNC = 'C';
                BPCRMSEQ.PTR = BPRSEQ;
                BPCRMSEQ.LEN = 289;
                S000_CALL_BPZRMSEQ();
                if (pgmRtn) return;
                BPCSGSEQ.SEQ = 1;
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICUAGT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
        if (CICUAGT.DATA.AGT_NO == null) CICUAGT.DATA.AGT_NO = "";
        JIBS_tmp_int = CICUAGT.DATA.AGT_NO.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) CICUAGT.DATA.AGT_NO += " ";
        if (CICUAGT.DATA.AGT_TYP == null) CICUAGT.DATA.AGT_TYP = "";
        JIBS_tmp_int = CICUAGT.DATA.AGT_TYP.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) CICUAGT.DATA.AGT_TYP += " ";
        CICUAGT.DATA.AGT_NO = CICUAGT.DATA.AGT_TYP + CICUAGT.DATA.AGT_NO.substring(7);
        if (CICUAGT.DATA.AGT_NO == null) CICUAGT.DATA.AGT_NO = "";
        JIBS_tmp_int = CICUAGT.DATA.AGT_NO.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) CICUAGT.DATA.AGT_NO += " ";
        JIBS_tmp_str[0] = "" + BPCSGSEQ.SEQ;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        CICUAGT.DATA.AGT_NO = CICUAGT.DATA.AGT_NO.substring(0, 8 - 1) + JIBS_tmp_str[0] + CICUAGT.DATA.AGT_NO.substring(8 + 15 - 1);
        CEP.TRC(SCCGWA, CICUAGT.DATA.AGT_NO);
    }
    public void T000_READ_CITAGT_INDEX() throws IOException,SQLException,Exception {
        CITAGT_RD = new DBParm();
        CITAGT_RD.TableName = "CITAGT";
        CITAGT_RD.col = "AGT_NO";
        CITAGT_RD.where = "AGT_TYP = :CIRAGT.AGT_TYP "
            + "AND ENTY_TYP = :CIRAGT.KEY.ENTY_TYP "
            + "AND ENTY_NO = :CIRAGT.KEY.ENTY_NO";
        CITAGT_RD.fst = true;
        IBS.READ(SCCGWA, CIRAGT, this, CITAGT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_AGT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_AGT_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITAGT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
    }
    public void S000_CALL_BPZRMSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MAINT-SEQ", BPCRMSEQ);
        CEP.TRC(SCCGWA, "NORMTL OR NOT");
        CEP.TRC(SCCGWA, BPCRMSEQ.RC);
        if (BPCRMSEQ.RETURN_INFO == 'D') {
            CEP.TRC(SCCGWA, "ZHENGJIE");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_EXIST, CICUAGT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AGT_TYP_NOT_DEF, CICUAGT.RC);
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICUAGT.RC);
            }
        }
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, WS_ERR_INFO);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (CICUAGT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "CICUAGT=");
            CEP.TRC(SCCGWA, CICUAGT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
