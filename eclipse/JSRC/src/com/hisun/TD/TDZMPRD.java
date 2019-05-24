package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDZMPRD {
    DBParm TDTPMMP_RD;
    String JIBS_tmp_str[] = new String[10];
    String K_AP_MMO = "TD";
    String K_PRD_FMT = "TD501";
    String K_PRDP_TYP = "PRDPR";
    String K_HIS_FMT = "TDCMPR";
    String K_HIS_RMK = "TD PRODUCT PARM MAINTENANCE";
    String K_SYS_ERR = "SC6001";
    String WS_MSGID = " ";
    TDZMPRD_CP_PROD_CD CP_PROD_CD = new TDZMPRD_CP_PROD_CD();
    char WS_PMMP_FLG = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    TDCMPR TDCMPR = new TDCMPR();
    TDCMPR TDCMPRO = new TDCMPR();
    TDCMPR TDCOMPR = new TDCMPR();
    TDRPMMP TDRPMMP = new TDRPMMP();
    SCCGWA SCCGWA;
    TDCMPRD TDCMPRD;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, TDCMPRD TDCMPRD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCMPRD = TDCMPRD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDZMPRD return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (TDCMPRD.FUNC == 'I') {
            B010_CHECK_INPUT_DATA();
            B030_QUERY_PROC();
            B300_OUTPUT_PROC();
        } else if (TDCMPRD.FUNC == 'A') {
            B010_CHECK_INPUT_DATA();
            B050_ADD_PROC();
            B110_WRITE_HIS_PROC();
            B300_OUTPUT_PROC();
        } else if (TDCMPRD.FUNC == 'M') {
            B010_CHECK_INPUT_DATA();
            B070_MODIFY_PROC();
            B110_WRITE_HIS_PROC();
            B300_OUTPUT_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + TDCMPRD.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCMPRD.PROD_CD);
        CEP.TRC(SCCGWA, TDCMPRD.CDESC);
        CEP.TRC(SCCGWA, TDCMPRD.EFF_DT);
        CEP.TRC(SCCGWA, TDCMPRD.EXP_DT);
        CEP.TRC(SCCGWA, TDCMPRD.BV_TYP_DESC);
        CEP.TRC(SCCGWA, TDCMPRD.DRAW_MTH_DESC);
        CEP.TRC(SCCGWA, TDCMPRD.CROS_CR_LMT);
        CEP.TRC(SCCGWA, TDCMPRD.CROS_DR_LMT);
        CEP.TRC(SCCGWA, TDCMPRD.MIN_CCYC);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
    }
    public void B030_QUERY_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
        CEP.TRC(SCCGWA, TDCMPRD.PROD_CD);
        TDRPMMP.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        TDRPMMP.KEY.PROD_CD = TDCMPRD.PROD_CD;
        T000_READ_TDTPMMP();
    }
    public void B050_ADD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRPMMP);
        TDRPMMP.KEY.PROD_CD = TDCMPRD.PROD_CD;
        TDRPMMP.CDESC = TDCMPRD.CDESC;
        CEP.TRC(SCCGWA, TDRPMMP.KEY.PROD_CD);
        CEP.TRC(SCCGWA, TDRPMMP.CDESC);
        R000_MOVE_TO_PARM();
        T000_WRITE_TDTPMMP();
    }
    public void B070_MODIFY_PROC() throws IOException,SQLException,Exception {
        TDRPMMP.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        TDRPMMP.KEY.PROD_CD = TDCMPRD.PROD_CD;
        T000_READUP_TDTPMMP();
        if (TDCMPRD.CDESC.equalsIgnoreCase(TDRPMMP.CDESC) 
            && TDCMPRD.EFF_DT == TDRPMMP.SDT 
            && TDCMPRD.EXP_DT == TDRPMMP.DDT 
            && TDCMPRD.BV_TYP_DESC.equalsIgnoreCase(TDRPMMP.BV_TYP_DESC) 
            && TDCMPRD.DRAW_MTH_DESC.equalsIgnoreCase(TDRPMMP.DRAW_MTH_DESC) 
            && TDCMPRD.CROS_CR_LMT == TDRPMMP.CROS_CR_LMT 
            && TDCMPRD.CROS_DR_LMT == TDRPMMP.CROS_DR_LMT 
            && TDCMPRD.MIN_CCYC.equalsIgnoreCase(TDRPMMP.CCY)) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_NO_MOD);
        }
        R000_MOVE_TO_PARM();
        T000_REWRITE_TDTPMMP();
        CEP.TRC(SCCGWA, TDRPMMP.SDT);
        CEP.TRC(SCCGWA, TDRPMMP.DDT);
        if (TDRPMMP.SDT >= TDRPMMP.DDT) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_EXPDT_LESS_EFFDT);
        }
        if (SCCGWA.COMM_AREA.AC_DATE >= TDRPMMP.DDT) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_EXPDT_LESS_ACDT);
        }
    }
    public void R000_MOVE_TO_PARM() throws IOException,SQLException,Exception {
        if (TDCMPRD.FUNC == 'A') {
            TDRPMMP.PROD_CD_M = TDCMPRD.PROD_CD_M;
            TDRPMMP.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            TDRPMMP.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (TDCMPRD.FUNC == 'M') {
            TDRPMMP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            TDRPMMP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRPMMP.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        }
        TDRPMMP.KEY.PROD_CD = TDCMPRD.PROD_CD;
        TDRPMMP.CDESC = TDCMPRD.CDESC;
        TDRPMMP.SDT = TDCMPRD.EFF_DT;
        TDRPMMP.DDT = TDCMPRD.EXP_DT;
        TDRPMMP.BV_TYP_DESC = TDCMPRD.BV_TYP_DESC;
        TDRPMMP.DRAW_MTH_DESC = TDCMPRD.DRAW_MTH_DESC;
        TDRPMMP.CROS_CR_LMT = TDCMPRD.CROS_CR_LMT;
        TDRPMMP.CROS_DR_LMT = TDCMPRD.CROS_DR_LMT;
        TDRPMMP.CCY = TDCMPRD.MIN_CCYC;
        CEP.TRC(SCCGWA, TDCMPRD.BV_TYP_DESC);
    }
    public void B110_WRITE_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.REF_NO = TDCMPRD.PROD_CD;
        BPCPNHIS.INFO.FMT_ID = K_HIS_FMT;
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMK;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        if (TDCMPRD.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            BPCPNHIS.INFO.NEW_DAT_PT = TDCMPR;
        } else if (TDCMPRD.FUNC == 'M') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.OLD_DAT_PT = TDCMPRO;
            BPCPNHIS.INFO.NEW_DAT_PT = TDCMPR;
        } else {
        }
        S000_CALL_BPZPNHIS();
    }
    public void B300_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCOMPR);
        TDCOMPR.FUNC = TDCMPRD.FUNC;
        TDCOMPR.PROD_CD = TDRPMMP.PROD_CD_M;
        TDCOMPR.CDESC = TDRPMMP.CDESC;
        CEP.TRC(SCCGWA, TDRPMMP.SDT);
        CEP.TRC(SCCGWA, TDRPMMP.DDT);
        TDCOMPR.SUM.SDT = TDRPMMP.SDT;
        TDCMPRD.EFF_DT = TDRPMMP.SDT;
        TDCOMPR.SUM.DDT = TDRPMMP.DDT;
        TDCMPRD.EXP_DT = TDRPMMP.DDT;
        TDCOMPR.SUM.BV_TYP_DESC = TDRPMMP.BV_TYP_DESC;
        TDCMPRD.BV_TYP_DESC = TDRPMMP.BV_TYP_DESC;
        TDCOMPR.SUM.DRAW_MTH_DESC = TDRPMMP.DRAW_MTH_DESC;
        TDCMPRD.DRAW_MTH_DESC = TDRPMMP.DRAW_MTH_DESC;
        TDCOMPR.SUM.CROS_CR_LMT = TDRPMMP.CROS_CR_LMT;
        TDCMPRD.CROS_CR_LMT = TDRPMMP.CROS_CR_LMT;
        TDCOMPR.SUM.CROS_DR_LMT = TDRPMMP.CROS_DR_LMT;
        TDCMPRD.CROS_DR_LMT = TDRPMMP.CROS_DR_LMT;
        TDCOMPR.SUM.CCY = TDRPMMP.CCY;
        CEP.TRC(SCCGWA, TDCOMPR.SUM.BV_TYP_DESC);
        CEP.TRC(SCCGWA, TDCOMPR.SUM.DRAW_MTH_DESC);
        CEP.TRC(SCCGWA, TDCOMPR.SUM.CROS_CR_LMT);
        CEP.TRC(SCCGWA, TDCOMPR.SUM.CROS_DR_LMT);
        CEP.TRC(SCCGWA, TDCOMPR.SUM.BV_TYP_DESC);
        if (TDCMPRD.FMT_FLG != 'N') {
            IBS.init(SCCGWA, SCCFMT);
            SCCFMT.FMTID = K_PRD_FMT;
            SCCFMT.DATA_PTR = TDCOMPR;
            SCCFMT.DATA_LEN = 143;
            IBS.FMT(SCCGWA, SCCFMT);
        }
    }
    public void T000_READ_TDTPMMP() throws IOException,SQLException,Exception {
        TDTPMMP_RD = new DBParm();
        TDTPMMP_RD.TableName = "TDTPMMP";
        TDTPMMP_RD.where = "IBS_AC_BK = :TDRPMMP.KEY.IBS_AC_BK "
            + "AND PROD_CD = :TDRPMMP.KEY.PROD_CD";
        IBS.READ(SCCGWA, TDRPMMP, this, TDTPMMP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_PMMP_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_PMMP_FLG = 'N';
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TDT_PMMP_REC_NOT_EXIT);
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_OTHER_ERR);
        }
    }
    public void T000_WRITE_TDTPMMP() throws IOException,SQLException,Exception {
        TDTPMMP_RD = new DBParm();
        TDTPMMP_RD.TableName = "TDTPMMP";
        TDTPMMP_RD.errhdl = true;
        IBS.WRITE(SCCGWA, TDRPMMP, TDTPMMP_RD);
    }
    if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
        CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TDT_PMMP_REC_EXIT);
    }
    public void T000_READUP_TDTPMMP() throws IOException,SQLException,Exception {
        TDTPMMP_RD = new DBParm();
        TDTPMMP_RD.TableName = "TDTPMMP";
        TDTPMMP_RD.where = "IBS_AC_BK = :TDRPMMP.KEY.IBS_AC_BK "
            + "AND PROD_CD = :TDRPMMP.KEY.PROD_CD";
        TDTPMMP_RD.upd = true;
        IBS.READ(SCCGWA, TDRPMMP, this, TDTPMMP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_PMMP_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_PMMP_FLG = 'N';
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TDT_PMMP_REC_NOT_EXIT);
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_OTHER_ERR);
        }
    }
    public void T000_REWRITE_TDTPMMP() throws IOException,SQLException,Exception {
        TDTPMMP_RD = new DBParm();
        TDTPMMP_RD.TableName = "TDTPMMP";
        IBS.REWRITE(SCCGWA, TDRPMMP, TDTPMMP_RD);
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
