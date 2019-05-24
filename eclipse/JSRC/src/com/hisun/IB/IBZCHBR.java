package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.IOException;
import java.sql.SQLException;

public class IBZCHBR {
    int JIBS_tmp_int;
    BigDecimal bigD;
    String JIBS_tmp_str[] = new String[10];
    DBParm IBTMST_RD;
    DBParm IBTTMAIN_RD;
    DBParm IBTTMST_RD;
    boolean pgmRtn = false;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    String K_IBTMST = "IBTMST  ";
    String K_IBTTMST = "IBTTMST ";
    String K_IBTTMAIN = "IBTTMAIN";
    short K_360_DAYS = 360;
    short K_365_DAYS = 365;
    short K_366_DAYS = 366;
    String K_OUTPUT_FMT = "IBB11";
    char WS_TABLE_REC = ' ';
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    BPCPQORG BPCPQORG = new BPCPQORG();
    CICQACRI CICQACRI = new CICQACRI();
    CICSACR CICSACR = new CICSACR();
    CICSACAC CICSACAC = new CICSACAC();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    CICACCU CICACCU = new CICACCU();
    CICQACAC CICQACAC = new CICQACAC();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    IBRMST IBRMST = new IBRMST();
    IBRTMST IBRTMST = new IBRTMST();
    IBRTMAIN IBRTMAIN = new IBRTMAIN();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBCCHBR IBCCHBR;
    public void MP(SCCGWA SCCGWA, IBCCHBR IBCCHBR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCCHBR = IBCCHBR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBZCHBR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, IBCCHBR.MIG_TYPE);
        if (IBCCHBR.MIG_TYPE == '1') {
            B020_AC_UPD_PROC();
            if (pgmRtn) return;
        } else if (IBCCHBR.MIG_TYPE == '2') {
            B030_ACO_UPD_PROC();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID MIG TYPE(" + IBCCHBR.MIG_TYPE + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCCHBR.OLD_BR);
        CEP.TRC(SCCGWA, IBCCHBR.NEW_BR);
        if (IBCCHBR.OLD_BR == 0) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.OLD_BR_M, IBCCHBR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (IBCCHBR.NEW_BR == 0) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.NEW_BR_M, IBCCHBR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_AC_UPD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = IBCCHBR.AC_NO;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        if (CICQACRI.O_DATA.O_CNTRCT_TYP.equalsIgnoreCase("IBDD")) {
            B021_IBDD_UPD_PROC();
            if (pgmRtn) return;
        } else {
            if (CICQACRI.O_DATA.O_CNTRCT_TYP.equalsIgnoreCase("IBTD")) {
                B022_IBTD_UPD_PROC();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.NOT_IB_AC, IBCCHBR.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B021_IBDD_UPD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRMST);
        IBRMST.KEY.AC_NO = IBCCHBR.AC_NO;
        T000_READ_IBTMST_UPD1();
        if (pgmRtn) return;
        if (WS_TABLE_REC == 'N') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.AC_NOEXIST, IBCCHBR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (IBRMST.POST_CTR == IBCCHBR.NEW_BR) {
            B023_UPD_CI_PROC();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBRMST.POST_CTR = IBCCHBR.NEW_BR;
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = IBCCHBR.NEW_BR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            IBRMST.OWNER_BK = "" + BPCPQORG.SUPR_BR;
            JIBS_tmp_int = IBRMST.OWNER_BK.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) IBRMST.OWNER_BK = "0" + IBRMST.OWNER_BK;
            T000_REWRITE_IBTMST();
            if (pgmRtn) return;
            B023_UPD_CI_PROC();
            if (pgmRtn) return;
            B024_GEN_VCH_PROC();
            if (pgmRtn) return;
        }
    }
    public void B022_IBTD_UPD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRTMAIN);
        IBRTMAIN.KEY.AC_NO = IBCCHBR.AC_NO;
        T000_READ_IBTTMAIN_UPD();
        if (pgmRtn) return;
        if (WS_TABLE_REC == 'N') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.AC_NOEXIST, IBCCHBR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (IBRTMAIN.OPEN_BR == IBCCHBR.NEW_BR) {
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBRTMAIN.OPEN_BR = IBCCHBR.NEW_BR;
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = IBCCHBR.NEW_BR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            IBRTMAIN.OWNER_BK = "" + BPCPQORG.SUPR_BR;
            JIBS_tmp_int = IBRTMAIN.OWNER_BK.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) IBRTMAIN.OWNER_BK = "0" + IBRTMAIN.OWNER_BK;
            T000_REWRITE_IBTTMAIN();
            if (pgmRtn) return;
            B023_UPD_CI_PROC();
            if (pgmRtn) return;
        }
    }
    public void B023_UPD_CI_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACR);
        CICSACR.FUNC = 'M';
        CICSACR.DATA.AGR_NO = IBCCHBR.AC_NO;
        CEP.TRC(SCCGWA, CICSACR.DATA.AGR_NO);
        CICSACR.DATA.OPN_BR = IBCCHBR.NEW_BR;
        CICSACR.DATA.OWNER_BK = BPCPQORG.SUPR_BR;
        S000_CALL_CIZSACR();
        if (pgmRtn) return;
    }
    public void B024_GEN_VCH_PROC() throws IOException,SQLException,Exception {
        if (IBRMST.L_VALUE_BAL != 0 
            || IBRMST.BUD_INT != 0) {
            IBS.init(SCCGWA, BPCPQPRD);
            BPCPQPRD.PRDT_CODE = IBRMST.PROD_CD;
            S000_CALL_BPZPQPRD();
            if (pgmRtn) return;
            IBS.init(SCCGWA, BPCPOEWA);
            BPCPOEWA.DATA.CNTR_TYPE = BPCPQPRD.PRDT_MODEL;
            BPCPOEWA.DATA.PROD_CODE = IBRMST.PROD_CD;
            BPCPOEWA.DATA.EVENT_CODE = "BC";
            BPCPOEWA.DATA.BR_OLD = IBCCHBR.OLD_BR;
            BPCPOEWA.DATA.BR_NEW = IBCCHBR.NEW_BR;
            BPCPOEWA.DATA.PAY_BR = IBCCHBR.NEW_BR;
            BPCPOEWA.DATA.CCY_INFO[1-1].CCY = IBRMST.CCY;
            BPCPOEWA.DATA.AMT_INFO[1-1].AMT = IBRMST.L_VALUE_BAL;
            BPCPOEWA.DATA.AMT_INFO[2-1].AMT = IBRMST.BUD_INT * 1;
            bigD = new BigDecimal(BPCPOEWA.DATA.AMT_INFO[2-1].AMT);
            BPCPOEWA.DATA.AMT_INFO[2-1].AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
            BPCPOEWA.DATA.AC_NO = IBRMST.ACO_AC;
            BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = IBRMST.KEY.AC_NO;
            S000_CALL_CIZACCU();
            if (pgmRtn) return;
            BPCPOEWA.DATA.CI_NO = CICACCU.DATA.CI_NO;
            BPCPOEWA.DATA.PAY_MAN = CICACCU.DATA.AC_CNM;
            S000_CALL_BPZPOEWA();
            if (pgmRtn) return;
        }
    }
    public void B030_ACO_UPD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'A';
        CICQACAC.DATA.ACAC_NO = IBCCHBR.ACO_AC;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        if (CICQACAC.O_DATA.O_ACR_DATA.O_CNTRCT_TYP.equalsIgnoreCase("IBDD")) {
            B031_IBDD_UPD_PROC();
            if (pgmRtn) return;
        } else {
            if (CICQACAC.O_DATA.O_ACR_DATA.O_CNTRCT_TYP.equalsIgnoreCase("IBTD")) {
                B032_IBTD_UPD_PROC();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.NOT_IB_AC, IBCCHBR.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B031_IBDD_UPD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRMST);
        IBRMST.ACO_AC = IBCCHBR.ACO_AC;
        CEP.TRC(SCCGWA, IBRMST.ACO_AC);
        T000_READ_IBTMST_UPD2();
        if (pgmRtn) return;
        if (WS_TABLE_REC == 'N') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.AC_NOEXIST, IBCCHBR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (IBRMST.POST_CTR == IBCCHBR.NEW_BR) {
            B033_UPD_CI_PROC();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBRMST.POST_CTR = IBCCHBR.NEW_BR;
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = IBCCHBR.NEW_BR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            IBRMST.OWNER_BK = "" + BPCPQORG.SUPR_BR;
            JIBS_tmp_int = IBRMST.OWNER_BK.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) IBRMST.OWNER_BK = "0" + IBRMST.OWNER_BK;
            T000_REWRITE_IBTMST();
            if (pgmRtn) return;
            B033_UPD_CI_PROC();
            if (pgmRtn) return;
            B024_GEN_VCH_PROC();
            if (pgmRtn) return;
        }
    }
    public void B032_IBTD_UPD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRTMST);
        IBRTMST.KEY.AC_NO = IBCCHBR.ACO_AC;
        CEP.TRC(SCCGWA, IBRTMST.KEY.AC_NO);
        T000_READ_IBTTMST_UPD();
        if (pgmRtn) return;
        if (WS_TABLE_REC == 'N') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.AC_NOEXIST, IBCCHBR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (IBRTMST.POST_CTR == IBCCHBR.NEW_BR) {
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBRTMST.POST_CTR = IBCCHBR.NEW_BR;
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = IBCCHBR.NEW_BR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            IBRTMST.OWNER_BK = "" + BPCPQORG.SUPR_BR;
            JIBS_tmp_int = IBRTMST.OWNER_BK.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) IBRTMST.OWNER_BK = "0" + IBRTMST.OWNER_BK;
            T000_REWRITE_IBTTMST();
            if (pgmRtn) return;
            B033_UPD_CI_PROC();
            if (pgmRtn) return;
            B034_GEN_VCH_PROC();
            if (pgmRtn) return;
        }
    }
    public void B033_UPD_CI_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACAC);
        CICSACAC.FUNC = 'M';
        CICSACAC.DATA.ACAC_NO = IBCCHBR.ACO_AC;
        CEP.TRC(SCCGWA, CICSACAC.DATA.ACAC_NO);
        CICSACAC.DATA.OPN_BR = IBCCHBR.NEW_BR;
        CEP.TRC(SCCGWA, CICSACAC.DATA.OPN_BR);
        CICSACAC.DATA.OWNER_BK = BPCPQORG.SUPR_BR;
        S000_CALL_CIZSACAC();
        if (pgmRtn) return;
    }
    public void B034_GEN_VCH_PROC() throws IOException,SQLException,Exception {
        if (IBRTMST.LBAL != 0 
            || IBRTMST.BUD_INT != 0) {
            IBS.init(SCCGWA, BPCPQPRD);
            BPCPQPRD.PRDT_CODE = IBRTMST.PROD_CD;
            S000_CALL_BPZPQPRD();
            if (pgmRtn) return;
            IBS.init(SCCGWA, BPCPOEWA);
            BPCPOEWA.DATA.CNTR_TYPE = BPCPQPRD.PRDT_MODEL;
            BPCPOEWA.DATA.PROD_CODE = IBRTMST.PROD_CD;
            BPCPOEWA.DATA.EVENT_CODE = "BC";
            BPCPOEWA.DATA.BR_OLD = IBCCHBR.OLD_BR;
            BPCPOEWA.DATA.BR_NEW = IBCCHBR.NEW_BR;
            BPCPOEWA.DATA.PAY_BR = IBCCHBR.NEW_BR;
            BPCPOEWA.DATA.CCY_INFO[1-1].CCY = IBRTMST.CCY;
            BPCPOEWA.DATA.AMT_INFO[1-1].AMT = IBRTMST.LBAL;
            BPCPOEWA.DATA.AMT_INFO[2-1].AMT = IBRTMST.BUD_INT * 1;
            bigD = new BigDecimal(BPCPOEWA.DATA.AMT_INFO[2-1].AMT);
            BPCPOEWA.DATA.AMT_INFO[2-1].AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
            BPCPOEWA.DATA.AC_NO = IBRTMST.KEY.AC_NO;
            BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = IBRTMST.PRIM_AC_NO;
            S000_CALL_CIZACCU();
            if (pgmRtn) return;
            BPCPOEWA.DATA.CI_NO = CICACCU.DATA.CI_NO;
            BPCPOEWA.DATA.PAY_MAN = CICACCU.DATA.AC_CNM;
            S000_CALL_BPZPOEWA();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI, true);
        if (CICQACRI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCCHBR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCCHBR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZSACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACR", CICSACR);
        if (CICSACR.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICSACR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCCHBR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZSACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACAC", CICSACAC);
        if (CICSACAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICSACAC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCCHBR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCCHBR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCCHBR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCCHBR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTMST_UPD1() throws IOException,SQLException,Exception {
        IBTMST_RD = new DBParm();
        IBTMST_RD.TableName = "IBTMST";
        IBTMST_RD.upd = true;
        IBS.READ(SCCGWA, IBRMST, IBTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTMST;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTMST_UPD2() throws IOException,SQLException,Exception {
        IBTMST_RD = new DBParm();
        IBTMST_RD.TableName = "IBTMST";
        IBTMST_RD.where = "ACO_AC = :IBRMST.ACO_AC";
        IBTMST_RD.upd = true;
        IBS.READ(SCCGWA, IBRMST, this, IBTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTMST;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_IBTMST() throws IOException,SQLException,Exception {
        IBTMST_RD = new DBParm();
        IBTMST_RD.TableName = "IBTMST";
        IBS.REWRITE(SCCGWA, IBRMST, IBTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTMST;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTTMAIN_UPD() throws IOException,SQLException,Exception {
        IBTTMAIN_RD = new DBParm();
        IBTTMAIN_RD.TableName = "IBTTMAIN";
        IBTTMAIN_RD.upd = true;
        IBS.READ(SCCGWA, IBRTMAIN, IBTTMAIN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTTMAIN;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_IBTTMAIN() throws IOException,SQLException,Exception {
        IBTTMAIN_RD = new DBParm();
        IBTTMAIN_RD.TableName = "IBTTMAIN";
        IBS.REWRITE(SCCGWA, IBRTMAIN, IBTTMAIN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTTMAIN;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTTMST_UPD() throws IOException,SQLException,Exception {
        IBTTMST_RD = new DBParm();
        IBTTMST_RD.TableName = "IBTTMST";
        IBTTMST_RD.upd = true;
        IBS.READ(SCCGWA, IBRTMST, IBTTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "111");
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "222");
            WS_TABLE_REC = 'N';
        } else {
            CEP.TRC(SCCGWA, "333");
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTTMST;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_IBTTMST() throws IOException,SQLException,Exception {
        IBTTMST_RD = new DBParm();
        IBTTMST_RD.TableName = "IBTTMST";
        IBS.REWRITE(SCCGWA, IBRTMST, IBTTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTTMST;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
