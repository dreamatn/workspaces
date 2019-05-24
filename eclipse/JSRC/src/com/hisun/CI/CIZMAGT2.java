package com.hisun.CI;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZMAGT2 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm CITBAS_RD;
    DBParm CITACR_RD;
    DBParm CITAGT_RD;
    brParm CITAGT_BR = new brParm();
    boolean pgmRtn = false;
    String K_HIS_RMK = "CI AGT INFO        ";
    String K_HIS_CPY = "CIRAGT";
    String K_SEQ_TYPE = "CIAGT";
    int K_EXP_DATE = 20991231;
    int WS_I = 0;
    String WS_MSGID = " ";
    String WS_ERR_INFO = " ";
    String WS_AGT_NO = " ";
    char WS_AGT_FLG = ' ';
    char WS_MX_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    CIRAGT CIRAGT = new CIRAGT();
    CIRAGT CIRAGTO = new CIRAGT();
    CIRAGT CIRAGTN = new CIRAGT();
    CICPAGT CICPAGT = new CICPAGT();
    CIRBAS CIRBAS = new CIRBAS();
    CIRACR CIRACR = new CIRACR();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRPRMT BPRPRMT = new BPRPRMT();
    CICUAGT CICUAGT = new CICUAGT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICMAGT2 CICMAGT2;
    public void MP(SCCGWA SCCGWA, CICMAGT2 CICMAGT2) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICMAGT2 = CICMAGT2;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZMAGT2 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CIRAGT);
        CICMAGT2.RC.RC_MMO = "CI";
        CICMAGT2.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICMAGT2.OPT);
        if (CICMAGT2.OPT == 'A') {
            B020_ADD_PROC();
            if (pgmRtn) return;
        } else if (CICMAGT2.OPT == 'M') {
            B030_MODIFY_PROC();
            if (pgmRtn) return;
        } else if (CICMAGT2.OPT == 'D') {
            B040_DELETE_PROC();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "操作标识输入错误");
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_ADD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRAGT);
        IBS.init(SCCGWA, CIRAGTN);
        R000_CHECK_CUSACR_STS();
        if (pgmRtn) return;
        R000_CHECK_AGT_PRMR();
        if (pgmRtn) return;
        if (CICPAGT.MUTEX_DATA[1-1].MX_AGT_TYP.trim().length() > 0) {
            R000_CHECK_MX_AGT_TYP();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CICMAGT2.DATA.AGT_NO);
        if (CICMAGT2.DATA.AGT_NO.trim().length() == 0) {
            R000_GET_ORDNO_PROC();
            if (pgmRtn) return;
        } else {
            WS_AGT_NO = CICMAGT2.DATA.AGT_NO;
        }
        CEP.TRC(SCCGWA, WS_AGT_NO);
        if (WS_AGT_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AGT_NO_NOT_GENERATE, CICMAGT2.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, CIRAGT);
            CIRAGT.KEY.AGT_NO = WS_AGT_NO;
            CIRAGT.KEY.ENTY_NO = CICMAGT2.DATA.ENTY_NO;
            CIRAGT.KEY.ENTY_TYP = CICMAGT2.DATA.ENTY_TYP;
            CIRAGT.AGT_STS = 'N';
            CEP.TRC(SCCGWA, CIRAGT.KEY.AGT_NO);
            CEP.TRC(SCCGWA, CIRAGT.KEY.ENTY_NO);
            CEP.TRC(SCCGWA, CIRAGT.KEY.ENTY_TYP);
            CEP.TRC(SCCGWA, CIRAGT.AGT_STS);
            T000_READ_CITAGT();
            if (pgmRtn) return;
            if (WS_AGT_FLG == 'N') {
                R000_TRANS_DATA_TO_TBL();
                if (pgmRtn) return;
                T000_WRITE_CITAGT();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AGT_INF_EXIST, CICMAGT2.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        IBS.CLONE(SCCGWA, CIRAGT, CIRAGTN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        R000_SAVE_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRAGT);
        IBS.init(SCCGWA, CIRAGTO);
        IBS.init(SCCGWA, CIRAGTN);
        R000_CHECK_CUSACR_STS();
        if (pgmRtn) return;
        R000_CHECK_AGT_PRMR();
        if (pgmRtn) return;
        if (CICPAGT.MUTEX_DATA[1-1].MX_AGT_TYP.trim().length() > 0) {
            R000_CHECK_MX_AGT_TYP();
            if (pgmRtn) return;
        }
        CIRAGT.AGT_STS = 'N';
        CIRAGT.KEY.ENTY_TYP = CICMAGT2.DATA.OLD_ENTY_TYP;
        CIRAGT.KEY.ENTY_NO = CICMAGT2.DATA.OLD_ENTY_NO;
        if (CICMAGT2.DATA.CHNL_AGT_NO.trim().length() > 0) {
            CIRAGT.CHNL_AGT_NO = CICMAGT2.DATA.CHNL_AGT_NO;
            T000_READ_CITAGT_UPD_BY_CAGT();
            if (pgmRtn) return;
            if (WS_AGT_FLG == 'N') {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AGT_INF_NOTFND, CICMAGT2.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                IBS.CLONE(SCCGWA, CIRAGT, CIRAGTO);
                R000_TRANS_DATA_TO_TBL();
                if (pgmRtn) return;
                T000_REWRITE_CITAGT();
                if (pgmRtn) return;
            }
        }
        IBS.CLONE(SCCGWA, CIRAGT, CIRAGTN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        R000_SAVE_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B040_DELETE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRAGT);
        IBS.init(SCCGWA, CIRAGTO);
        IBS.init(SCCGWA, CIRAGTN);
        R000_CHECK_CUSACR_STS();
        if (pgmRtn) return;
        CIRAGT.KEY.ENTY_NO = CICMAGT2.DATA.ENTY_NO;
        CIRAGT.KEY.ENTY_TYP = CICMAGT2.DATA.ENTY_TYP;
        CIRAGT.AGT_STS = 'N';
        if (CICMAGT2.DATA.CHNL_AGT_NO.trim().length() > 0) {
            CIRAGT.CHNL_AGT_NO = CICMAGT2.DATA.CHNL_AGT_NO;
            T000_READ_CITAGT_UPD_BY_CAGT();
            if (pgmRtn) return;
            if (WS_AGT_FLG == 'N') {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AGT_INF_NOTFND, CICMAGT2.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        IBS.CLONE(SCCGWA, CIRAGT, CIRAGTO);
        CIRAGT.AGT_STS = 'C';
        CIRAGT.EXP_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CIRAGT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRAGT.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRAGT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_REWRITE_CITAGT();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRAGT, CIRAGTN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        R000_SAVE_HIS_PROC();
        if (pgmRtn) return;
    }
    public void R000_CHECK_CUSACR_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        IBS.init(SCCGWA, CIRACR);
        CIRBAS.KEY.CI_NO = CICMAGT2.DATA.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        if (CIRBAS.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_IS_CLOSE_STS, CICMAGT2.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRACR);
        CEP.TRC(SCCGWA, CICMAGT2.DATA.ENTY_TYP);
        CEP.TRC(SCCGWA, CICMAGT2.DATA.ENTY_NO);
        if (CICMAGT2.DATA.ENTY_TYP != '0') {
            CIRACR.KEY.AGR_NO = CICMAGT2.DATA.ENTY_NO;
            CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
            T000_READ_CITACR();
            if (pgmRtn) return;
            if (CIRACR.STS == '1') {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_IS_CLOSED, CICMAGT2.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_CHECK_AGT_PRMR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICPAGT);
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, BPRPRMT);
        CEP.TRC(SCCGWA, CICMAGT2.DATA.AGT_TYP);
        CEP.TRC(SCCGWA, K_SEQ_TYPE);
        BPRPRMT.KEY.TYP = K_SEQ_TYPE;
        BPRPRMT.KEY.CD = CICMAGT2.DATA.AGT_TYP;
        BPCPRMR.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICPAGT);
        CEP.TRC(SCCGWA, CICPAGT.AGT_LVL);
    }
    public void R000_GET_ORDNO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICUAGT);
        CICUAGT.FUNC = 'N';
        CICUAGT.DATA.AGT_TYP = CICMAGT2.DATA.AGT_TYP;
        S000_CALL_CIZUAGT();
        if (pgmRtn) return;
        WS_AGT_NO = CICUAGT.DATA.AGT_NO;
    }
    public void R000_CHECK_MX_AGT_TYP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRAGT);
        CIRAGT.KEY.ENTY_TYP = CICMAGT2.DATA.ENTY_TYP;
        CIRAGT.KEY.ENTY_NO = CICMAGT2.DATA.ENTY_NO;
        T000_STARTBR_CITAGT_ENTY_CI();
        if (pgmRtn) return;
        T000_READNEXT_CITAGT();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MX_FLG = 'N';
        }
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            for (WS_I = 1; WS_I <= 12 
                && CICPAGT.MUTEX_DATA[WS_I-1].MX_AGT_TYP.trim().length() != 0; WS_I += 1) {
                if (CICPAGT.MUTEX_DATA[WS_I-1].MX_AGT_TYP.trim().length() > 0) {
                    if (CICPAGT.MUTEX_DATA[WS_I-1].MX_AGT_TYP.equalsIgnoreCase(CIRAGT.AGT_TYP)) {
                        IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AGT_TYP_MUTEX, CICMAGT2.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                }
            }
            T000_READNEXT_CITAGT();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITAGT();
        if (pgmRtn) return;
    }
    public void R000_SAVE_HIS_PROC() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.CI_NO = CICMAGT2.DATA.CI_NO;
        BPCPNHIS.INFO.AC = CICMAGT2.DATA.ENTY_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMK;
        BPCPNHIS.INFO.FMT_ID = K_HIS_CPY;
        BPCPNHIS.INFO.FMT_ID_LEN = 664;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRAGTO;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRAGTN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_TO_TBL() throws IOException,SQLException,Exception {
        CIRAGT.CI_NO = CICMAGT2.DATA.CI_NO;
        CIRAGT.AGT_TYP = CICMAGT2.DATA.AGT_TYP;
        CIRAGT.KEY.ENTY_TYP = CICMAGT2.DATA.ENTY_TYP;
        CIRAGT.KEY.ENTY_NO = CICMAGT2.DATA.ENTY_NO;
        CIRAGT.CHNL_AGT_NO = CICMAGT2.DATA.CHNL_AGT_NO;
        CIRAGT.AGT_LVL = CICMAGT2.DATA.AGT_LVL;
        if (CICMAGT2.DATA.EFF_DATE == 0) {
            CIRAGT.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            CIRAGT.EFF_DATE = CICMAGT2.DATA.EFF_DATE;
        }
        if (CICMAGT2.DATA.EXP_DATE == 0) {
            CIRAGT.EXP_DATE = K_EXP_DATE;
        } else {
            CIRAGT.EXP_DATE = CICMAGT2.DATA.EXP_DATE;
        }
        CIRAGT.SGN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CIRAGT.AGT_STS = 'N';
        CIRAGT.ORG_NO = "" + SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        JIBS_tmp_int = CIRAGT.ORG_NO.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) CIRAGT.ORG_NO = "0" + CIRAGT.ORG_NO;
        CIRAGT.SGN_CHNL = SCCGWA.COMM_AREA.CHNL;
        CIRAGT.REMARK = CICMAGT2.DATA.REMARK;
        if (CICMAGT2.OPT == 'A') {
            CIRAGT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRAGT.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRAGT.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        }
        CIRAGT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRAGT.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRAGT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.col = "STSW,CI_TYP";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_IS_NOT_EXIST);
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_IS_NOT_EXIST, CICMAGT2.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTEXIST, CICMAGT2.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITAGT() throws IOException,SQLException,Exception {
        CITAGT_RD = new DBParm();
        CITAGT_RD.TableName = "CITAGT";
        IBS.READ(SCCGWA, CIRAGT, CITAGT_RD);
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
    public void T000_WRITE_CITAGT() throws IOException,SQLException,Exception {
        CITAGT_RD = new DBParm();
        CITAGT_RD.TableName = "CITAGT";
        IBS.WRITE(SCCGWA, CIRAGT, CITAGT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITAGT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITAGT_UPD() throws IOException,SQLException,Exception {
        CITAGT_RD = new DBParm();
        CITAGT_RD.TableName = "CITAGT";
        CITAGT_RD.eqWhere = "AGT_NO , ENTY_TYP , ENTY_NO , AGT_STS";
        CITAGT_RD.upd = true;
        IBS.READ(SCCGWA, CIRAGT, CITAGT_RD);
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
    public void T000_READ_CITAGT_UPD_BY_CAGT() throws IOException,SQLException,Exception {
        CITAGT_RD = new DBParm();
        CITAGT_RD.TableName = "CITAGT";
        CITAGT_RD.eqWhere = "CHNL_AGT_NO , ENTY_TYP , ENTY_NO , AGT_STS";
        CITAGT_RD.upd = true;
        IBS.READ(SCCGWA, CIRAGT, CITAGT_RD);
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
    public void T000_READ_CITAGT_UPDPK() throws IOException,SQLException,Exception {
        CITAGT_RD = new DBParm();
        CITAGT_RD.TableName = "CITAGT";
        CITAGT_RD.eqWhere = "AGT_NO , ENTY_TYP , ENTY_NO , AGT_STS";
        CITAGT_RD.upd = true;
        IBS.READ(SCCGWA, CIRAGT, CITAGT_RD);
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
    public void T000_REWRITE_CITAGT() throws IOException,SQLException,Exception {
        CITAGT_RD = new DBParm();
        CITAGT_RD.TableName = "CITAGT";
        IBS.REWRITE(SCCGWA, CIRAGT, CITAGT_RD);
    }
    public void T000_STARTBR_CITAGT_ENTY_CI() throws IOException,SQLException,Exception {
        CITAGT_BR.rp = new DBParm();
        CITAGT_BR.rp.TableName = "CITAGT";
        CITAGT_BR.rp.where = "ENTY_NO = :CIRAGT.KEY.ENTY_NO "
            + "AND ENTY_TYP = :CIRAGT.KEY.ENTY_TYP "
            + "AND AGT_STS = 'N'";
        IBS.STARTBR(SCCGWA, CIRAGT, this, CITAGT_BR);
    }
    public void T000_READNEXT_CITAGT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRAGT, this, CITAGT_BR);
    }
    public void T000_ENDBR_CITAGT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITAGT_BR);
    }
    public void S000_CALL_CIZUAGT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-GNL-AGT-NO", CICUAGT);
        if (CICUAGT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICUAGT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICMAGT2.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AGT_TYP_NOT_DEF, CICMAGT2.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICMAGT2.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPNHIS.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICMAGT2.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, WS_ERR_INFO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
