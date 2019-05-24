package com.hisun.PN;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class PNZSMPRD {
    int JIBS_tmp_int;
    DBParm PNTPROD_RD;
    String JIBS_tmp_str[] = new String[10];
    String CPN_S_PNZSMPRD = "PN-S-MTM-BLL-PRD    ";
    String CPN_PARM_MT = "BP-PARM-MAINTAIN    ";
    String CPN_PARM_BR = "BP-PARM-BROWSE      ";
    String CPN_R_MBRW_PARM = "BP-R-MBRW-PARM      ";
    String CPN_REC_NHIS = "BP-REC-NHIS";
    String K_PRDPR_TYPE = "PRDPR";
    String K_OUTPUT_FMT = "PN100";
    String K_HIS_CPB_NM = "PNVMPRD";
    String K_HIS_RMKS = "PNPRD PARM MAINTENANCE";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    short WS_CNT = 0;
    PNZSMPRD_CP_PROD_CD CP_PROD_CD = new PNZSMPRD_CP_PROD_CD();
    int WS_AC_DATE = 0;
    char WS_FUNC_FLG = ' ';
    PNCMSG_ERROR_MSG PNCMSG_ERROR_MSG = new PNCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    PNCMPRDO PNCMPRDO = new PNCMPRDO();
    PNCMPRDN PNCMPRDN = new PNCMPRDN();
    PNCOMPRD PNCOMPRD = new PNCOMPRD();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCSUBS SCCSUBS = new SCCSUBS();
    PNRPROD PNRPROD = new PNRPROD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PNCSMPRD PNCSMPRD;
    public void MP(SCCGWA SCCGWA, PNCSMPRD PNCSMPRD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PNCSMPRD = PNCSMPRD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "PNZSMPRD return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, PNCSMPRD.FUNC);
        if (PNCSMPRD.FUNC == 'Q'
            || PNCSMPRD.FUNC == 'I') {
            B030_QUERY_PROCESS();
            B130_TRANS_DATA_OUTPUT();
        } else if (PNCSMPRD.FUNC == 'A') {
            CEP.TRC(SCCGWA, "SMPRD-ADD");
            CEP.TRC(SCCGWA, PNCSMPRD.FUNC);
            B010_CHECK_INPUT();
            B050_CREATE_PROCESS();
            B110_WRITE_NHIS_PROC();
            B130_TRANS_DATA_OUTPUT();
        } else if (PNCSMPRD.FUNC == 'M') {
            CEP.TRC(SCCGWA, "SMPRD-MODIFY");
            CEP.TRC(SCCGWA, PNCSMPRD.FUNC);
            B010_CHECK_INPUT();
            B030_QUERY_PROCESS();
            if (PNRPROD.KEY.EFF_DATE < SCCGWA.COMM_AREA.AC_DATE) {
                B050_CREATE_PROCESS();
                B110_WRITE_NHIS_PROC();
                B130_TRANS_DATA_OUTPUT();
            } else {
                B070_MODIFY_PROCESS();
                B110_WRITE_NHIS_PROC();
                B130_TRANS_DATA_OUTPUT();
            }
        } else if (PNCSMPRD.FUNC == 'D') {
            CEP.TRC(SCCGWA, "SMPRD-DELETE");
            CEP.TRC(SCCGWA, PNCSMPRD.FUNC);
            B010_CHECK_INPUT();
            B090_DELETE_PROCESS();
            B110_WRITE_NHIS_PROC();
            B130_TRANS_DATA_OUTPUT();
        } else {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
        }
    }
    public void B030_QUERY_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
        IBS.init(SCCGWA, PNRPROD);
        CP_PROD_CD.PROD_ACC_CENT = 999999;
        CP_PROD_CD.PROD_PRDT_CODE = PNCSMPRD.PROD_CD;
        PNRPROD.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        PNRPROD.KEY.CODE = IBS.CLS2CPY(SCCGWA, CP_PROD_CD);
        CEP.TRC(SCCGWA, PNRPROD.KEY.CODE);
        CEP.TRC(SCCGWA, CP_PROD_CD);
        T000_READ_PROD_INF();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, PNCSMPRD.CTL_INFO.CCY);
        if (PNCSMPRD.PROD_CD.trim().length() == 0) {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_PRD_NOT_PNT);
        }
        if (PNCSMPRD.PROD_MOD.trim().length() == 0) {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_PRMMD_MUST_IPT);
        }
        if (PNCSMPRD.FUNC == 'A' 
            || PNCSMPRD.FUNC == 'M') {
            if (PNCSMPRD.PROD_CNM.trim().length() == 0) {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_PRDNM_NOT_IPT);
            }
            if (PNCSMPRD.EFF_DATE == 0) {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_EFFDT_NOT_IPT);
            }
            if (PNCSMPRD.EXP_DATE == 0) {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_EXPDT_NOT_IPT);
            }
            if (PNCSMPRD.EFF_DATE >= PNCSMPRD.EXP_DATE) {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_EXPDT_LESS_EFFDT);
            }
            if (SCCGWA.COMM_AREA.AC_DATE >= PNCSMPRD.EXP_DATE) {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_EXPDT_LESS_ACDT);
            }
            if (PNCSMPRD.CTL_INFO.CCY.trim().length() == 0) {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_CCY_MUST_IPT);
            }
            if (PNCSMPRD.CTL_INFO.PAY_TERM == ' ') {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_TERM_NOT_IPT);
            }
            if (PNCSMPRD.CTL_INFO.AUTO_REL.trim().length() == 0) {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_AU_RELDAYS_NOT_IPT);
            }
        }
    }
    public void B050_CREATE_PROCESS() throws IOException,SQLException,Exception {
        R000_TRANS_COMM_DATA_TO_VIEW();
        CEP.TRC(SCCGWA, PNCSMPRD.EFF_DATE);
        CEP.TRC(SCCGWA, PNCSMPRD.EXP_DATE);
        T000_WRITE_PROD_INF();
    }
    public void B070_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNRPROD);
        PNRPROD.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        CP_PROD_CD.PROD_ACC_CENT = 999999;
        CP_PROD_CD.PROD_PRDT_CODE = PNCSMPRD.PROD_CD;
        PNRPROD.KEY.CODE = IBS.CLS2CPY(SCCGWA, CP_PROD_CD);
        PNRPROD.KEY.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_READ_UPDATE_PROD_INF();
        PNRPROD.EXP_DATE = PNCSMPRD.EXP_DATE;
        PNRPROD.CDESC = PNCSMPRD.PROD_CNM;
        PNRPROD.CCY = PNCSMPRD.CTL_INFO.CCY;
        PNRPROD.PAY_TERM = PNCSMPRD.CTL_INFO.PAY_TERM;
        PNRPROD.AUTO_REL = PNCSMPRD.CTL_INFO.AUTO_REL;
        PNRPROD.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        PNRPROD.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        PNRPROD.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_PROD_INF();
    }
    public void R000_TRANS_HIS_DATA_OLD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNCMPRDO);
        PNCMPRDO.PARM_CODE = PNRPROD.KEY.CODE;
        PNCMPRDO.PRD_CNM = PNRPROD.CDESC;
        PNCMPRDO.EFF_DATE = PNRPROD.KEY.EFF_DATE;
        PNCMPRDO.EXP_DATE = PNRPROD.EXP_DATE;
        PNCMPRDO.CCY = PNRPROD.CCY;
        PNCMPRDO.PAY_TERM = PNRPROD.PAY_TERM;
        PNCMPRDO.AUTO_REL = PNRPROD.AUTO_REL;
    }
    public void R000_TRANS_HIS_DATA_NEW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNCMPRDN);
        PNCMPRDN.PARM_CODE = PNCSMPRD.PROD_CD;
        PNCMPRDN.PRD_CNM = PNCSMPRD.PROD_CNM;
        PNCMPRDN.EFF_DATE = PNCSMPRD.EFF_DATE;
        PNCMPRDN.EXP_DATE = PNCSMPRD.EXP_DATE;
        PNCMPRDN.CCY = PNCSMPRD.CTL_INFO.CCY;
        PNCMPRDN.PAY_TERM = PNCSMPRD.CTL_INFO.PAY_TERM;
        PNCMPRDN.AUTO_REL = PNCSMPRD.CTL_INFO.AUTO_REL;
    }
    public void B090_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNRPROD);
        PNRPROD.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        CP_PROD_CD.PROD_ACC_CENT = 999999;
        CP_PROD_CD.PROD_PRDT_CODE = PNCSMPRD.PROD_CD;
        PNRPROD.KEY.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_READ_UPDATE_PROD_INF();
        T000_DELETE_PROD_INF();
    }
    public void R000_TRANS_COMM_DATA_TO_VIEW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNRPROD);
        PNRPROD.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        if (PNRPROD.KEY.CODE == null) PNRPROD.KEY.CODE = "";
        JIBS_tmp_int = PNRPROD.KEY.CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) PNRPROD.KEY.CODE += " ";
        PNRPROD.KEY.CODE = "999999" + PNRPROD.KEY.CODE.substring(6);
        if (PNRPROD.KEY.CODE == null) PNRPROD.KEY.CODE = "";
        JIBS_tmp_int = PNRPROD.KEY.CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) PNRPROD.KEY.CODE += " ";
        if (PNCSMPRD.PROD_CD == null) PNCSMPRD.PROD_CD = "";
        JIBS_tmp_int = PNCSMPRD.PROD_CD.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) PNCSMPRD.PROD_CD += " ";
        PNRPROD.KEY.CODE = PNRPROD.KEY.CODE.substring(0, 7 - 1) + PNCSMPRD.PROD_CD + PNRPROD.KEY.CODE.substring(7 + 10 - 1);
        PNRPROD.CDESC = PNCSMPRD.PROD_CNM;
        PNRPROD.KEY.EFF_DATE = PNCSMPRD.EFF_DATE;
        PNRPROD.EXP_DATE = PNCSMPRD.EXP_DATE;
        PNRPROD.CCY = PNCSMPRD.CTL_INFO.CCY;
        PNRPROD.PAY_TERM = PNCSMPRD.CTL_INFO.PAY_TERM;
        PNRPROD.AUTO_REL = PNCSMPRD.CTL_INFO.AUTO_REL;
        PNRPROD.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        PNRPROD.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        PNRPROD.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void B110_WRITE_NHIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.REF_NO = PNCSMPRD.PROD_CD;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.JRNNO);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.FMT_ID = K_HIS_CPB_NM;
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMKS;
        BPCPNHIS.INFO.FMT_ID_LEN = 194;
        CEP.TRC(SCCGWA, PNCSMPRD.FUNC);
        if (PNCSMPRD.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            BPCPNHIS.INFO.NEW_DAT_PT = PNRPROD;
            BPCPNHIS.INFO.OLD_DAT_PT = PNRPROD;
        }
        if (PNCSMPRD.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
            BPCPNHIS.INFO.OLD_DAT_PT = PNRPROD;
            BPCPNHIS.INFO.NEW_DAT_PT = PNRPROD;
        }
        if (PNCSMPRD.FUNC == 'M') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.OLD_DAT_PT = PNCMPRDO;
            BPCPNHIS.INFO.NEW_DAT_PT = PNCMPRDN;
        }
        S000_CALL_BPZPNHIS();
    }
    public void B130_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNCOMPRD);
        PNCOMPRD.FUNC = PNCSMPRD.FUNC;
        PNCOMPRD.PARM_CODE = PNRPROD.KEY.CODE;
        PNCSMPRD.PROD_CD = PNRPROD.KEY.CODE;
        PNCOMPRD.PRD_CNM = PNRPROD.CDESC;
        PNCSMPRD.PROD_CNM = PNRPROD.CDESC;
        PNCOMPRD.EFF_DATE = PNRPROD.KEY.EFF_DATE;
        PNCSMPRD.EFF_DATE = PNRPROD.KEY.EFF_DATE;
        PNCOMPRD.EXP_DATE = PNRPROD.EXP_DATE;
        PNCSMPRD.EXP_DATE = PNRPROD.EXP_DATE;
        PNCOMPRD.CCY = PNRPROD.CCY;
        PNCSMPRD.CTL_INFO.CCY = PNRPROD.CCY;
        PNCOMPRD.PAY_TERM = PNRPROD.PAY_TERM;
        PNCSMPRD.CTL_INFO.PAY_TERM = PNRPROD.PAY_TERM;
        CEP.TRC(SCCGWA, PNRPROD.AUTO_REL);
        PNCOMPRD.AUTO_REL = PNRPROD.AUTO_REL;
        PNCSMPRD.CTL_INFO.AUTO_REL = PNRPROD.AUTO_REL;
        CEP.TRC(SCCGWA, PNCOMPRD.AUTO_REL);
        if (PNCSMPRD.FUNC != 'I') {
            IBS.init(SCCGWA, SCCFMT);
            SCCFMT.FMTID = K_OUTPUT_FMT;
            SCCFMT.DATA_PTR = PNCOMPRD;
            SCCFMT.DATA_LEN = 119;
            IBS.FMT(SCCGWA, SCCFMT);
        }
    }
    public void T000_READ_PROD_INF() throws IOException,SQLException,Exception {
        PNTPROD_RD = new DBParm();
        PNTPROD_RD.TableName = "PNTPROD";
        PNTPROD_RD.where = "IBS_AC_BK = :PNRPROD.KEY.IBS_AC_BK "
            + "AND CODE = :PNRPROD.KEY.CODE";
        PNTPROD_RD.fst = true;
        PNTPROD_RD.order = "EFF_DATE DESC";
        IBS.READ(SCCGWA, PNRPROD, this, PNTPROD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_MPRD_REC_NOTFND);
        }
    }
    public void T000_WRITE_PROD_INF() throws IOException,SQLException,Exception {
        PNTPROD_RD = new DBParm();
        PNTPROD_RD.TableName = "PNTPROD";
        PNTPROD_RD.errhdl = true;
        IBS.WRITE(SCCGWA, PNRPROD, PNTPROD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_MPRD_REC_ALEXIST);
        }
    }
    public void T000_READ_UPDATE_PROD_INF() throws IOException,SQLException,Exception {
        PNTPROD_RD = new DBParm();
        PNTPROD_RD.TableName = "PNTPROD";
        PNTPROD_RD.upd = true;
        IBS.READ(SCCGWA, PNRPROD, PNTPROD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_MPRD_REC_NOTFND);
        }
    }
    public void T000_REWRITE_PROD_INF() throws IOException,SQLException,Exception {
        PNRPROD.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        PNRPROD.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        PNRPROD.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        PNTPROD_RD = new DBParm();
        PNTPROD_RD.TableName = "PNTPROD";
        IBS.REWRITE(SCCGWA, PNRPROD, PNTPROD_RD);
    }
    public void T000_DELETE_PROD_INF() throws IOException,SQLException,Exception {
        PNTPROD_RD = new DBParm();
        PNTPROD_RD.TableName = "PNTPROD";
        IBS.DELETE(SCCGWA, PNRPROD, PNTPROD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_MPRD_REC_NOTFND);
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC);
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
