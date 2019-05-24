package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSACRL {
    int JIBS_tmp_int;
    DBParm CITACR_RD;
    DBParm CITACRL_RD;
    boolean pgmRtn = false;
    String WS_MSGID = " ";
    String WS_ERR_INFO = " ";
    short WS_FLD_NO = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CIRACR CIRACR = new CIRACR();
    CIRACRL CIRACRL = new CIRACRL();
    CIRACRL CIRACRLO = new CIRACRL();
    CIRACRL CIRACRLN = new CIRACRL();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCTPCL SCCTPCL = new SCCTPCL();
    int WS_AC_DATE = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSACRL CICSACRL;
    public void MP(SCCGWA SCCGWA, CICSACRL CICSACRL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSACRL = CICSACRL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSACRL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        WS_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (CICSACRL.FUNC == 'A') {
            B020_ADD_ACRL_INF();
            if (pgmRtn) return;
        } else if (CICSACRL.FUNC == 'M') {
            B030_MOD_ACRL_INF();
            if (pgmRtn) return;
        } else if (CICSACRL.FUNC == 'D') {
            B040_DEL_ACRL_INF();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_ERROR, CICSACRL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSACRL.DATA.AC_NO);
        CEP.TRC(SCCGWA, CICSACRL.DATA.AC_REL);
        CEP.TRC(SCCGWA, CICSACRL.DATA.REL_AC_NO);
        if (CICSACRL.DATA.AC_NO.trim().length() == 0 
            || CICSACRL.DATA.AC_REL.trim().length() == 0 
            || CICSACRL.DATA.REL_AC_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "LACK OF NORMAL INFORMATION");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICSACRL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CICSACRL.DATA.AC_NO;
        T000_READ_CITACR_EXIST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CICSACRL.DATA.REL_AC_NO;
        T000_READ_CITACR_EXIST();
        if (pgmRtn) return;
    }
    public void B020_ADD_ACRL_INF() throws IOException,SQLException,Exception {
        if (CICSACRL.DATA.DEFAULT == '1') {
            IBS.init(SCCGWA, CIRACRL);
            CIRACRL.KEY.AC_NO = CICSACRL.DATA.AC_NO;
            CIRACRL.KEY.AC_REL = CICSACRL.DATA.AC_REL;
            T000_READ_CITACRL_DEFAULT();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CICSACRL.DATA.AC_REL);
        if (CICSACRL.DATA.AC_REL.equalsIgnoreCase("05") 
            || CICSACRL.DATA.AC_REL.equalsIgnoreCase("06")) {
            IBS.init(SCCGWA, CIRACRL);
            CIRACRL.KEY.AC_REL = CICSACRL.DATA.AC_REL;
            CIRACRL.KEY.REL_AC_NO = CICSACRL.DATA.REL_AC_NO;
            T000_READ_CITACRL_BY_R_AC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            CEP.TRC(SCCGWA, CICSACRL.DATA.AC_REL);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0' 
                    && CICSACRL.DATA.AC_REL.equalsIgnoreCase("05")) {
                CEP.TRC(SCCGWA, "NCD EXIST");
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_NCD_EXIST, CICSACRL.RC);
                Z_RET();
                if (pgmRtn) return;
            } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0' 
                    && CICSACRL.DATA.AC_REL.equalsIgnoreCase("06")) {
                CEP.TRC(SCCGWA, "XXT EXIST");
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_XXT_EXIST, CICSACRL.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
            }
        }
        IBS.init(SCCGWA, CIRACRL);
        IBS.init(SCCGWA, CIRACRLO);
        IBS.init(SCCGWA, CIRACRLN);
        CIRACRL.KEY.AC_NO = CICSACRL.DATA.AC_NO;
        CIRACRL.KEY.AC_REL = CICSACRL.DATA.AC_REL;
        CIRACRL.KEY.REL_AC_NO = CICSACRL.DATA.REL_AC_NO;
        T000_READ_CITACRL_EXIST();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (CIRACRL.EXP_DT == SCCGWA.COMM_AREA.AC_DATE) {
                IBS.CLONE(SCCGWA, CIRACRL, CIRACRLO);
                CIRACRL.EXP_DT = 0;
                CIRACRL.KEY.REL_STS = '0';
                CEP.TRC(SCCGWA, CICSACRL.DATA.DEFAULT);
                CIRACRL.KEY.REL_CTL = "" + 0;
                JIBS_tmp_int = CIRACRL.KEY.REL_CTL.length();
                for (int i=0;i<0-JIBS_tmp_int;i++) CIRACRL.KEY.REL_CTL = "0" + CIRACRL.KEY.REL_CTL;
                if (CICSACRL.DATA.DEFAULT == '1') {
                    if (CIRACRL.KEY.REL_CTL == null) CIRACRL.KEY.REL_CTL = "";
                    JIBS_tmp_int = CIRACRL.KEY.REL_CTL.length();
                    for (int i=0;i<20-JIBS_tmp_int;i++) CIRACRL.KEY.REL_CTL += " ";
                    CIRACRL.KEY.REL_CTL = "1" + CIRACRL.KEY.REL_CTL.substring(1);
                }
                CIRACRL.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                CIRACRL.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                CIRACRL.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                T000_REWRITE_CITACRL();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, CIRACRL.EXP_DT);
                IBS.CLONE(SCCGWA, CIRACRL, CIRACRLN);
                IBS.init(SCCGWA, BPCPNHIS);
                BPCPNHIS.INFO.TX_TYP = 'M';
                R000_WRT_HIS_PROC();
                if (pgmRtn) return;
                R000_DATA_TRANS_TO_ECIF();
                if (pgmRtn) return;
            } else {
                CEP.TRC(SCCGWA, "ACRL INF EXIST");
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACRL_INF_FND, CICSACRL.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            CEP.TRC(SCCGWA, CICSACRL.DATA.EFF_DT);
            if (CICSACRL.DATA.EFF_DT == 0) {
                CICSACRL.DATA.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
            }
            R000_DATA_TRANS_TO_TBL_ADD();
            if (pgmRtn) return;
            T000_WRITE_CITACRL();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, CIRACRL, CIRACRLN);
            IBS.init(SCCGWA, BPCPNHIS);
            BPCPNHIS.INFO.TX_TYP = 'A';
            R000_WRT_HIS_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_MOD_ACRL_INF() throws IOException,SQLException,Exception {
        if (CICSACRL.DATA.DEFAULT == '1') {
            IBS.init(SCCGWA, CIRACRL);
            CIRACRL.KEY.AC_NO = CICSACRL.DATA.AC_NO;
            CIRACRL.KEY.AC_REL = CICSACRL.DATA.AC_REL;
            T000_READ_CITACRL_DEFAULT();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRACRL);
        IBS.init(SCCGWA, CIRACRLO);
        IBS.init(SCCGWA, CIRACRLN);
        CIRACRL.KEY.AC_NO = CICSACRL.DATA.AC_NO;
        CIRACRL.KEY.AC_REL = CICSACRL.DATA.AC_REL;
        CIRACRL.KEY.REL_AC_NO = CICSACRL.DATA.REL_AC_NO;
        T000_READ_CITACRL_UPD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRACRL, CIRACRLO);
        CEP.TRC(SCCGWA, CICSACRL.DATA.DEFAULT);
        if (CICSACRL.DATA.DEFAULT == '0') {
            if (CIRACRL.KEY.REL_CTL == null) CIRACRL.KEY.REL_CTL = "";
            JIBS_tmp_int = CIRACRL.KEY.REL_CTL.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CIRACRL.KEY.REL_CTL += " ";
            CIRACRL.KEY.REL_CTL = "0" + CIRACRL.KEY.REL_CTL.substring(1);
        } else if (CICSACRL.DATA.DEFAULT == '1') {
            if (CIRACRL.KEY.REL_CTL == null) CIRACRL.KEY.REL_CTL = "";
            JIBS_tmp_int = CIRACRL.KEY.REL_CTL.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CIRACRL.KEY.REL_CTL += " ";
            CIRACRL.KEY.REL_CTL = "1" + CIRACRL.KEY.REL_CTL.substring(1);
        } else {
            CEP.TRC(SCCGWA, "SACRL-DEFAULT INPUT ERROR");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, CICSACRL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CIRACRL.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRACRL.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRACRL.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_REWRITE_CITACRL();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRACRL, CIRACRLN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        R000_WRT_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B040_DEL_ACRL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACRL);
        IBS.init(SCCGWA, CIRACRLO);
        IBS.init(SCCGWA, CIRACRLN);
        CIRACRL.KEY.AC_NO = CICSACRL.DATA.AC_NO;
        CIRACRL.KEY.AC_REL = CICSACRL.DATA.AC_REL;
        CIRACRL.KEY.REL_AC_NO = CICSACRL.DATA.REL_AC_NO;
        T000_READ_CITACRL_UPD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRACRL, CIRACRLO);
        CIRACRL.KEY.REL_STS = '1';
        CIRACRL.EXP_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRACRL.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRACRL.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRACRL.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_REWRITE_CITACRL();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRACRL, CIRACRLN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        R000_WRT_HIS_PROC();
        if (pgmRtn) return;
    }
    public void R000_DATA_TRANS_TO_TBL_ADD() throws IOException,SQLException,Exception {
        CIRACRL.KEY.AC_NO = CICSACRL.DATA.AC_NO;
        CIRACRL.KEY.AC_REL = CICSACRL.DATA.AC_REL;
        CIRACRL.KEY.REL_AC_NO = CICSACRL.DATA.REL_AC_NO;
        CIRACRL.KEY.REL_STS = '0';
        CEP.TRC(SCCGWA, CICSACRL.DATA.DEFAULT);
        CIRACRL.KEY.REL_CTL = "" + 0;
        JIBS_tmp_int = CIRACRL.KEY.REL_CTL.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) CIRACRL.KEY.REL_CTL = "0" + CIRACRL.KEY.REL_CTL;
        if (CICSACRL.DATA.DEFAULT == '1') {
            if (CIRACRL.KEY.REL_CTL == null) CIRACRL.KEY.REL_CTL = "";
            JIBS_tmp_int = CIRACRL.KEY.REL_CTL.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CIRACRL.KEY.REL_CTL += " ";
            CIRACRL.KEY.REL_CTL = "1" + CIRACRL.KEY.REL_CTL.substring(1);
        }
        CIRACRL.EFF_DT = CICSACRL.DATA.EFF_DT;
        CIRACRL.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRACRL.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRACRL.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRACRL.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRACRL.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRACRL.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
    }
    public void R000_WRT_HIS_PROC() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.FMT_ID = "CIRACRL";
        BPCPNHIS.INFO.FMT_ID_LEN = 173;
        BPCPNHIS.INFO.AC = CICSACRL.DATA.AC_NO;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRACRLO;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRACRLN;
    }
    public void R000_DATA_TRANS_TO_ECIF() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPNHIS.RC);
        }
    }
    public void S000_CALL_SCZTPCL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-LINK-EXTSERV", SCCTPCL);
    }
    public void T000_READ_CITACR_EXIST() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTEXIST, CICSACRL.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACRL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITACRL_EXIST() throws IOException,SQLException,Exception {
        CITACRL_RD = new DBParm();
        CITACRL_RD.TableName = "CITACRL";
        CITACRL_RD.eqWhere = "AC_NO, AC_REL, REL_AC_NO";
        CITACRL_RD.where = "EXP_DT = 0 "
            + "OR EXP_DT >= :WS_AC_DATE";
        CITACRL_RD.upd = true;
        IBS.READ(SCCGWA, CIRACRL, this, CITACRL_RD);
    }
    public void T000_READ_CITACRL_BY_R_AC() throws IOException,SQLException,Exception {
        CITACRL_RD = new DBParm();
        CITACRL_RD.TableName = "CITACRL";
        CITACRL_RD.eqWhere = "AC_REL, REL_AC_NO";
        CITACRL_RD.where = "REL_STS = '0'";
        IBS.READ(SCCGWA, CIRACRL, this, CITACRL_RD);
    }
    public void T000_READ_CITACRL_DEFAULT() throws IOException,SQLException,Exception {
        CITACRL_RD = new DBParm();
        CITACRL_RD.TableName = "CITACRL";
        CITACRL_RD.eqWhere = "AC_NO , AC_REL";
        CITACRL_RD.where = "( EXP_DT = 0 "
            + "OR EXP_DT > :WS_AC_DATE ) "
            + "AND SUBSTR ( REL_CTL , 1 , 1 ) = '1'";
        IBS.READ(SCCGWA, CIRACRL, this, CITACRL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "DEFAULT ACRL INF EXIST");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_DEFAULT_ACRL_EXIST, CICSACRL.RC);
            Z_RET();
            if (pgmRtn) return;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACRL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_WRITE_CITACRL() throws IOException,SQLException,Exception {
        CITACRL_RD = new DBParm();
        CITACRL_RD.TableName = "CITACRL";
        IBS.WRITE(SCCGWA, CIRACRL, CITACRL_RD);
    }
    public void T000_READ_CITACRL_UPD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIRACRL.KEY.AC_NO);
        CEP.TRC(SCCGWA, CIRACRL.KEY.AC_REL);
        CEP.TRC(SCCGWA, CIRACRL.KEY.REL_AC_NO);
        CITACRL_RD = new DBParm();
        CITACRL_RD.TableName = "CITACRL";
        CITACRL_RD.eqWhere = "AC_NO, AC_REL, REL_AC_NO";
        CITACRL_RD.where = "EXP_DT = 0 "
            + "OR EXP_DT > :WS_AC_DATE";
        CITACRL_RD.upd = true;
        IBS.READ(SCCGWA, CIRACRL, this, CITACRL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ACRL INF NOT FOUND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACRL_INF_NOTFND, CICSACRL.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACRL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_CITACRL() throws IOException,SQLException,Exception {
        CITACRL_RD = new DBParm();
        CITACRL_RD.TableName = "CITACRL";
        IBS.REWRITE(SCCGWA, CIRACRL, CITACRL_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
