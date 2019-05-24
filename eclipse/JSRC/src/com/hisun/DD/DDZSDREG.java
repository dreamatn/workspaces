package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSDREG {
    brParm DDTDREG_BR = new brParm();
    DBParm DDTMST_RD;
    DBParm DDTDORM_RD;
    DBParm DDTCCY_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT1 = "DD622";
    String K_OUTPUT_FMT2 = "DD623";
    String WS_ERR_MSG = " ";
    DDZSDREG_WS_OUTPUT_INF WS_OUTPUT_INF = new DDZSDREG_WS_OUTPUT_INF();
    char WS_DREG_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    DDRDREG DDRDREG = new DDRDREG();
    DDCODREG DDCODREG = new DDCODREG();
    DDCIDREG DDCIDREG = new DDCIDREG();
    DDRDORM DDRDORM = new DDRDORM();
    DDCIDORM DDCIDORM = new DDCIDORM();
    CICACCU CICACCU = new CICACCU();
    SCCBINF SCCBINF = new SCCBINF();
    BPCPRGST BPCPRGST = new BPCPRGST();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    CICQACAC CICQACAC = new CICQACAC();
    DDCSFDOR DDCSFDOR = new DDCSFDOR();
    SCCGWA SCCGWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    DDCSDREG DDCSDREG;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DDCSDREG DDCSDREG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSDREG = DDCSDREG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSDREG return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSDREG.FUNC);
        if (DDCSDREG.FUNC != 'B') {
            B010_GET_ACAC_PROC();
            if (pgmRtn) return;
        }
        if (DDCSDREG.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (DDCSDREG.FUNC == 'A') {
            B020_ADD_PROCESS();
            if (pgmRtn) return;
        } else if (DDCSDREG.FUNC == 'M') {
            B030_UPDATE_PROCESS();
            if (pgmRtn) return;
        } else if (DDCSDREG.FUNC == 'D') {
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (DDCSDREG.FUNC == 'B') {
            B050_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else if (DDCSDREG.FUNC == 'S') {
            B060_SET_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCSDREG.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_GET_ACAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = DDCSDREG.AC;
        CICQACAC.DATA.CCY_ACAC = DDCSDREG.CCY;
        CICQACAC.DATA.CR_FLG = DDCSDREG.CCY_TYPE;
        CICQACAC.FUNC = 'C';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        R000_CHECK_DATA_VALIDITY();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDCIDREG);
        DDCIDREG.DATA.KEY.AC = DDCSDREG.AC;
        DDCIDREG.DATA.KEY.CCY = DDCSDREG.CCY;
        DDCIDREG.DATA.KEY.CCY_TYPE = DDCSDREG.CCY_TYPE;
        DDCIDREG.DATA.BR = DDCSDREG.BR;
        DDCIDREG.OPT = 'I';
        S000_CALL_DDZIDREG();
        if (pgmRtn) return;
        R000_GET_CCY_INFO();
        if (pgmRtn) return;
        R000_GET_CI_INFO();
        if (pgmRtn) return;
        R000_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B020_ADD_PROCESS() throws IOException,SQLException,Exception {
        R000_CHECK_DATA_VALIDITY();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDCIDREG);
        DDCIDREG.DATA.KEY.AC = DDCSDREG.AC;
        DDCIDREG.DATA.KEY.CCY = DDCSDREG.CCY;
        DDCIDREG.DATA.KEY.CCY_TYPE = DDCSDREG.CCY_TYPE;
        DDCIDREG.DATA.STS = DDCSDREG.STS;
        DDCIDREG.DATA.BR = DDCSDREG.BR;
        DDCIDREG.OPT = 'A';
        S000_CALL_DDZIDREG();
        if (pgmRtn) return;
        R000_GET_CCY_INFO();
        if (pgmRtn) return;
        R000_GET_CI_INFO();
        if (pgmRtn) return;
        R000_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B030_UPDATE_PROCESS() throws IOException,SQLException,Exception {
        R000_CHECK_DATA_VALIDITY();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDCIDREG);
        DDCIDREG.DATA.KEY.AC = DDCSDREG.AC;
        DDCIDREG.DATA.KEY.CCY = DDCSDREG.CCY;
        DDCIDREG.DATA.KEY.CCY_TYPE = DDCSDREG.CCY_TYPE;
        DDCIDREG.DATA.STS = DDCSDREG.STS;
        DDCIDREG.DATA.BR = DDCSDREG.BR;
        DDCIDREG.OPT = 'U';
        S000_CALL_DDZIDREG();
        if (pgmRtn) return;
        R000_GET_CCY_INFO();
        if (pgmRtn) return;
        R000_GET_CI_INFO();
        if (pgmRtn) return;
        R000_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B040_DELETE_PROCESS() throws IOException,SQLException,Exception {
        R000_CHECK_DATA_VALIDITY();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDCIDREG);
        DDCIDREG.DATA.KEY.AC = DDCSDREG.AC;
        DDCIDREG.DATA.KEY.CCY = DDCSDREG.CCY;
        DDCIDREG.DATA.KEY.CCY_TYPE = DDCSDREG.CCY_TYPE;
        DDCIDREG.DATA.STS = DDCSDREG.STS;
        DDCIDREG.DATA.BR = DDCSDREG.BR;
        DDCIDREG.OPT = 'X';
        S000_CALL_DDZIDREG();
        if (pgmRtn) return;
        B040_30_WRITE_DDTDORM();
        if (pgmRtn) return;
        R000_GET_CCY_INFO();
        if (pgmRtn) return;
        R000_GET_CI_INFO();
        if (pgmRtn) return;
        B040_10_READUPD_DDTMST();
        if (pgmRtn) return;
        B040_20_UPDATE_DDTMST();
        if (pgmRtn) return;
        R000_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B060_SET_PROCESS() throws IOException,SQLException,Exception {
        R000_CHECK_DATA_VALIDITY();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDCIDREG);
        DDCIDREG.DATA.KEY.AC = DDCSDREG.AC;
        DDCIDREG.DATA.KEY.CCY = DDCSDREG.CCY;
        DDCIDREG.DATA.KEY.CCY_TYPE = DDCSDREG.CCY_TYPE;
        DDCIDREG.DATA.STS = DDCSDREG.STS;
        DDCIDREG.DATA.BR = DDCSDREG.BR;
        DDCIDREG.DATA.FLG = DDCSDREG.DOM_FLG;
        CEP.TRC(SCCGWA, DDCSDREG.AC);
        CEP.TRC(SCCGWA, DDCSDREG.CCY);
        CEP.TRC(SCCGWA, DDCSDREG.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCSDREG.STS);
        CEP.TRC(SCCGWA, DDCIDREG.DATA.FLG);
        DDCIDREG.OPT = 'E';
        S000_CALL_DDZIDREG();
        if (pgmRtn) return;
        R000_GET_CCY_INFO();
        if (pgmRtn) return;
        R000_GET_CI_INFO();
        if (pgmRtn) return;
        B040_10_READUPD_DDTMST();
        if (pgmRtn) return;
        B040_30_UPDATE_DDTMST();
        if (pgmRtn) return;
        R000_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B040_30_UPDATE_DDTMST() throws IOException,SQLException,Exception {
        if (DDCSDREG.STS == '1') {
            DDRMST.AC_STS = 'N';
        }
        T000_REWRITE_REC_PROC();
        if (pgmRtn) return;
    }
    public void B040_10_READUPD_DDTMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSDREG.AC);
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDRCCY.CUS_AC;
        T000_READ_UPDATE_PROC();
        if (pgmRtn) return;
        if (DDCSDREG.FUNC == 'S' 
            && DDCSDREG.STS == '2' 
            && DDRMST.AC_STS != 'W') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B040_20_UPDATE_DDTMST() throws IOException,SQLException,Exception {
        DDRMST.AC_STS = 'N';
        T000_REWRITE_REC_PROC();
        if (pgmRtn) return;
    }
    public void B040_30_WRITE_DDTDORM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRDORM);
        DDRDORM.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRDORM.KEY.BR_NO = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRDORM.KEY.TYPE = '2';
        T000_READ_DDTDORM();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            S000_CALL_DDZIDORM();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B050_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        B060_CHECK_AUTHORITY();
        if (pgmRtn) return;
        B050_10_CHECK_DATA_VALIDITY();
        if (pgmRtn) return;
        B050_20_BEGIN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        B050_30_STARTBR_DDTDREG();
        if (pgmRtn) return;
        B050_40_READNEXT_DDTDREG();
        if (pgmRtn) return;
        while (WS_DREG_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            R000_GET_CCY_INFO();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDRCCY.CUS_AC);
            R000_GET_CI_INFO();
            if (pgmRtn) return;
            B050_50_OUTPUT_DETAIL();
            if (pgmRtn) return;
            B050_40_READNEXT_DDTDREG();
            if (pgmRtn) return;
        }
        B050_60_ENDBR_DDTDREG();
        if (pgmRtn) return;
    }
    public void B050_10_CHECK_DATA_VALIDITY() throws IOException,SQLException,Exception {
        if (DDCSDREG.FUNC == 'A' 
            && DDCSDREG.BR == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BR_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B050_20_BEGIN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 660;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B050_30_STARTBR_DDTDREG() throws IOException,SQLException,Exception {
        T000_STARTBR_DDTDREG();
        if (pgmRtn) return;
    }
    public void B050_40_READNEXT_DDTDREG() throws IOException,SQLException,Exception {
        T000_READNEXT_DDTDREG();
        if (pgmRtn) return;
    }
    public void B050_60_ENDBR_DDTDREG() throws IOException,SQLException,Exception {
        T000_ENDBR_DDTDREG();
        if (pgmRtn) return;
    }
    public void B050_50_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT_INF);
        WS_OUTPUT_INF.WS_AC = DDRCCY.CUS_AC;
        WS_OUTPUT_INF.WS_AC_CNM = CICACCU.DATA.AC_CNM;
        WS_OUTPUT_INF.WS_CI_OIC = CICACCU.DATA.CI_OIC;
        WS_OUTPUT_INF.WS_CCY = DDRCCY.CCY;
        WS_OUTPUT_INF.WS_CCY_TYPE = DDRCCY.CCY_TYPE;
        WS_OUTPUT_INF.WS_DATE = DDRDREG.KEY.APP_DATE;
        WS_OUTPUT_INF.WS_JRNNO = DDRDREG.KEY.JRNNO;
        WS_OUTPUT_INF.WS_STS = DDRDREG.STS;
        WS_OUTPUT_INF.WS_CURR_BAL = DDRCCY.CURR_BAL;
        WS_OUTPUT_INF.WS_BAL = DDRDREG.BAL;
        WS_OUTPUT_INF.WS_INT = DDRDREG.INT;
        WS_OUTPUT_INF.WS_INT_BAL = DDRDREG.INT_BAL;
        WS_OUTPUT_INF.WS_FLG = DDRDREG.FLG;
        WS_OUTPUT_INF.WS_OPN_DT = DDRCCY.OPEN_DATE;
        WS_OUTPUT_INF.WS_BR = DDRDREG.BR;
        WS_OUTPUT_INF.WS_W_DT = DDRDREG.W_DT;
        WS_OUTPUT_INF.WS_W_BR = DDRDREG.W_BR;
        WS_OUTPUT_INF.WS_W_TLR = DDRDREG.W_TLR;
        WS_OUTPUT_INF.WS_D_DT = DDRDREG.D_DT;
        WS_OUTPUT_INF.WS_D_BR = DDRDREG.D_BR;
        WS_OUTPUT_INF.WS_D_TLR = DDRDREG.D_TLR;
        WS_OUTPUT_INF.WS_T_DT = DDRDREG.T_DT;
        WS_OUTPUT_INF.WS_T_BR = DDRDREG.T_BR;
        WS_OUTPUT_INF.WS_T_TLR = DDRDREG.T_TLR;
        WS_OUTPUT_INF.WS_P_DT = DDRDREG.P_DT;
        WS_OUTPUT_INF.WS_P_BR = DDRDREG.P_BR;
        WS_OUTPUT_INF.WS_P_TLR = DDRDREG.P_TLR;
        WS_OUTPUT_INF.WS_N_DT = DDRDREG.N_DT;
        WS_OUTPUT_INF.WS_N_BR = DDRDREG.N_BR;
        WS_OUTPUT_INF.WS_N_TLR = DDRDREG.N_TLR;
        WS_OUTPUT_INF.WS_NTF_FLG = DDRDREG.NTF_FLG;
        WS_OUTPUT_INF.WS_NTF_DT = DDRDREG.NTF_DT;
        WS_OUTPUT_INF.WS_NTF_NUM = DDRDREG.NTF_NUM;
        WS_OUTPUT_INF.WS_FLG1 = DDRDREG.FLG1;
        WS_OUTPUT_INF.WS_FLG2 = DDRDREG.FLG2;
        WS_OUTPUT_INF.WS_RCD_STS = DDRDREG.RCD_STS;
        WS_OUTPUT_INF.WS_REMARKS = DDRDREG.REMARKS;
        CEP.TRC(SCCGWA, DDRDREG.KEY.AC);
        CEP.TRC(SCCGWA, DDRDREG.STS);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_INF);
        SCCMPAG.DATA_LEN = 660;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B060_CHECK_AUTHORITY() throws IOException,SQLException,Exception {
        if (DDCSDREG.BR == SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
        } else {
            S000_CALL_BPZPRGST();
            if (pgmRtn) return;
        }
    }
    public void R000_GET_CI_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDRCCY.CUS_AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
    }
    public void R000_GET_CCY_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        if (DDCSDREG.FUNC == 'B') {
            DDRCCY.KEY.AC = DDRDREG.KEY.AC;
        }
        CEP.TRC(SCCGWA, DDRDREG.KEY.AC);
        T000_READ_DDTCCY();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_DDTDREG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRDREG);
        DDRDREG.BR = DDCSDREG.BR;
        DDRDREG.RCD_STS = 'N';
        WS_DREG_FLG = 'N';
        CEP.TRC(SCCGWA, DDCSDREG.BR);
        CEP.TRC(SCCGWA, DDRDREG.STS);
        CEP.TRC(SCCGWA, DDCSDREG.AC_NO);
        if (DDCSDREG.AC_NO.trim().length() == 0) {
            DDTDREG_BR.rp = new DBParm();
            DDTDREG_BR.rp.TableName = "DDTDREG";
            DDTDREG_BR.rp.where = "BR = :DDRDREG.BR "
                + "AND STS = '1' "
                + "AND RCD_STS = :DDRDREG.RCD_STS";
            IBS.STARTBR(SCCGWA, DDRDREG, this, DDTDREG_BR);
        } else {
            IBS.init(SCCGWA, DDRCCY);
            DDRCCY.CUS_AC = DDCSDREG.AC_NO;
            T000_READ_DDTCCY_2();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
            DDRDREG.KEY.AC = DDRCCY.KEY.AC;
            DDTDREG_BR.rp = new DBParm();
            DDTDREG_BR.rp.TableName = "DDTDREG";
            DDTDREG_BR.rp.where = "BR = :DDRDREG.BR "
                + "AND STS = '1' "
                + "AND AC = :DDRDREG.KEY.AC "
                + "AND RCD_STS = :DDRDREG.RCD_STS";
            IBS.STARTBR(SCCGWA, DDRDREG, this, DDTDREG_BR);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, DDRDREG.KEY.AC);
    }
    public void T000_READNEXT_DDTDREG() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRDREG, this, DDTDREG_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DREG_FLG = 'Y';
        } else {
            WS_DREG_FLG = 'N';
        }
    }
    public void T000_ENDBR_DDTDREG() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTDREG_BR);
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.col = "AC_STS,LAST_DATE,LAST_TLR, UPDTBL_DATE,UPDTBL_TLR";
        DDTMST_RD.upd = true;
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        DDRMST.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.REWRITE(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void T000_READ_DDTDORM() throws IOException,SQLException,Exception {
        DDTDORM_RD = new DBParm();
        DDTDORM_RD.TableName = "DDTDORM";
        IBS.READ(SCCGWA, DDRDORM, DDTDORM_RD);
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READ_DDTCCY_2() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.eqWhere = "CUS_AC";
        DDTCCY_RD.fst = true;
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void R000_CHECK_DATA_VALIDITY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSDREG.AC);
        if (DDCSDREG.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSDREG.FUNC == 'D' 
            && DDCSDREG.STS != '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DORM_AC_NOT_REMOVE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CHK_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCLWD);
        BPCOCLWD.CAL_CODE = "CN  ";
        BPCOCLWD.DAYE_FLG = 'Y';
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        BPCOCLWD.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, BPCOCLWD.DATE1);
        BPCOCLWD.DAYS = -30;
        CEP.TRC(SCCGWA, BPCOCLWD.DAYS);
        S000_CALL_BPZPCLWD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCOCLWD.DATE2);
    }
    public void R000_OUTPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCIDREG);
        CEP.TRC(SCCGWA, DDCIDREG.DATA.KEY.AC);
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_OIC);
        CEP.TRC(SCCGWA, DDCIDREG.DATA.DATE);
        CEP.TRC(SCCGWA, DDCIDREG.DATA.STS);
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        CEP.TRC(SCCGWA, DDCIDREG.DATA.FLG);
        CEP.TRC(SCCGWA, DDCIDREG.DATA.BR);
        IBS.init(SCCGWA, DDCODREG);
        DDCODREG.AC = DDCIDREG.DATA.KEY.AC;
        DDCODREG.AC_CNM = CICACCU.DATA.AC_CNM;
        DDCODREG.CI_OIC = CICACCU.DATA.CI_OIC;
        DDCODREG.DATE = DDCIDREG.DATA.DATE;
        DDCODREG.JRNNO = DDCIDREG.DATA.JRNNO;
        DDCODREG.STS = DDCIDREG.DATA.STS;
        DDCODREG.CURR_BAL = DDRCCY.CURR_BAL;
        DDCODREG.BAL = DDCIDREG.DATA.BAL;
        DDCODREG.INT = DDCIDREG.DATA.INT;
        DDCODREG.INT_BAL = DDCIDREG.DATA.INT_BAL;
        DDCODREG.FLG = DDCIDREG.DATA.FLG;
        DDCODREG.BR = DDCIDREG.DATA.BR;
        DDCODREG.W_DT = DDCIDREG.DATA.W_DT;
        DDCODREG.W_BR = DDCIDREG.DATA.W_BR;
        DDCODREG.W_TLR = DDCIDREG.DATA.W_TLR;
        DDCODREG.D_DT = DDCIDREG.DATA.D_DT;
        DDCODREG.D_BR = DDCIDREG.DATA.D_BR;
        DDCODREG.D_TLR = DDCIDREG.DATA.D_TLR;
        DDCODREG.T_DT = DDCIDREG.DATA.T_DT;
        DDCODREG.T_BR = DDCIDREG.DATA.T_BR;
        DDCODREG.T_TLR = DDCIDREG.DATA.T_TLR;
        DDCODREG.P_DT = DDCIDREG.DATA.P_DT;
        DDCODREG.P_BR = DDCIDREG.DATA.P_BR;
        DDCODREG.P_TLR = DDCIDREG.DATA.P_TLR;
        DDCODREG.N_DT = DDCIDREG.DATA.N_DT;
        DDCODREG.N_BR = DDCIDREG.DATA.N_BR;
        DDCODREG.N_TLR = DDCIDREG.DATA.N_TLR;
        DDCODREG.NTF_FLG = DDCIDREG.DATA.NTF_FLG;
        DDCODREG.NTF_DT = DDCIDREG.DATA.NTF_DT;
        DDCODREG.NTF_NUM = DDCIDREG.DATA.NTF_NUM;
        DDCODREG.FLG1 = DDCIDREG.DATA.FLG1;
        DDCODREG.FLG2 = DDCIDREG.DATA.FLG2;
        DDCODREG.RCD_STS = DDCIDREG.DATA.RCD_STS;
        DDCODREG.REMARKS = DDCIDREG.DATA.REMARKS;
        CEP.TRC(SCCGWA, DDCODREG);
        IBS.init(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, DDCSDREG.FUNC);
        if (DDCSDREG.FUNC == 'I') {
            CEP.TRC(SCCGWA, "NEXT");
            SCCFMT.FMTID = K_OUTPUT_FMT1;
        } else {
            CEP.TRC(SCCGWA, "FINIAL");
            SCCFMT.FMTID = K_OUTPUT_FMT2;
        }
        SCCFMT.DATA_PTR = DDCODREG;
        SCCFMT.DATA_LEN = 660;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIDREG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SRC-DDZIDREG", DDCIDREG);
        if (DDCIDREG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIDREG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDCIDREG.RC.RC_CODE);
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRGST);
        BPCPRGST.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCPRGST.BR1 = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPRGST.BR2 = DDCSDREG.BR;
        CEP.TRC(SCCGWA, BPCPRGST.BR1);
        CEP.TRC(SCCGWA, BPCPRGST.BR2);
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG-STATION", BPCPRGST);
        CEP.TRC(SCCGWA, BPCPRGST.FLAG);
        CEP.TRC(SCCGWA, BPCPRGST.LVL_RELATION);
        CEP.TRC(SCCGWA, BPCPRGST.BRANCH_FLG);
        if (BPCPRGST.FLAG == 'Y' 
            && BPCPRGST.LVL_RELATION == 'A') {
        } else {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BR_OPT_AUT_LIMIT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIDORM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIDORM);
        DDCIDORM.DATA.DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCIDORM.DATA.BR_NO = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDCIDORM.DATA.TYPE = '2';
        DDCIDORM.DATA.END_DT = SCCGWA.COMM_AREA.AC_DATE;
        DDCIDORM.OPT = 'A';
        IBS.CALLCPN(SCCGWA, "DD-SRC-DDZIDORM", DDCIDORM);
        if (DDCIDORM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIDORM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_DDZSFDOR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSFDOR", DDCSFDOR);
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY  ", BPCOCLWD);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOCLWD.RC);
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
