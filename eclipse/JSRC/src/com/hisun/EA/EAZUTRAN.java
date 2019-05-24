package com.hisun.EA;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class EAZUTRAN {
    int JIBS_tmp_int;
    brParm EATWLST_BR = new brParm();
    brParm EATACLNK_BR = new brParm();
    DBParm EATACLNK_RD;
    String K_AP_MMO = "EA";
    String K_SYS_ERR = "SC6001";
    String WS_ERR_MSG = " ";
    short WS_RC = 0;
    short WS_RC_DISP = 0;
    String WS_TERM = " ";
    EAZUTRAN_REDEFINES5 REDEFINES5 = new EAZUTRAN_REDEFINES5();
    char WS_AC_CHK_FLG = ' ';
    char WS_NME_CHK_FLG = ' ';
    char WS_DC_FLG = ' ';
    char WS_WLST_FLG = ' ';
    char WS_AC_PASS_FLG = ' ';
    char WS_NME_PASS_FLG = ' ';
    String WS_IRAT_CD = " ";
    String WS_DISPLAY = " ";
    short WS_SEQ = 0;
    short WS_DEC = 0;
    short WS_LEN = 0;
    short WS_MTHS = 0;
    short WS_I = 0;
    short WS_J = 0;
    EACMSG_ERROR_MSG EACMSG_ERROR_MSG = new EACMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCBINF SCCBINF = new SCCBINF();
    EACTRAN EACTRAN = new EACTRAN();
    BPCPRMR BPCPRMR = new BPCPRMR();
    EARACLNK EARACLNK = new EARACLNK();
    EARWLST EARWLST = new EARWLST();
    SCCGWA SCCGWA;
    EACUTRAN EACUTRAN;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, EACUTRAN EACUTRAN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.EACUTRAN = EACUTRAN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "OUTCDINT");
        CEP.TRC(SCCGWA, "EAZUTRAN return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, EACUTRAN.FUNC);
        if (EACUTRAN.FUNC == 'D') {
            B100_D_CNTL();
        } else {
            if (EACUTRAN.FUNC == 'C') {
                B200_C_CNTL();
            } else {
                WS_ERR_MSG = EACMSG_ERROR_MSG.EA_FUNC_ERROR;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, "OUT-RAT-WSRAT");
    }
    public void B100_D_CNTL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, EACUTRAN.AC_FLG);
        CEP.TRC(SCCGWA, EACUTRAN.MMO);
        CEP.TRC(SCCGWA, EACUTRAN.AC_NME);
        CEP.TRC(SCCGWA, EACUTRAN.OPP_AC_NME);
        if (EACUTRAN.AC_FLG == '2') {
            WS_AC_CHK_FLG = 'Y';
            WS_NME_CHK_FLG = 'Y';
            WS_DC_FLG = 'D';
            R_CHK_WLST_PROC();
            if (WS_WLST_FLG != 'Y') {
                R_CHK_AC_NME_PROC();
            }
        } else {
            if (EACUTRAN.MMO.equalsIgnoreCase("P115") 
                && !EACUTRAN.AC_NME.equalsIgnoreCase(EACUTRAN.OPP_AC_NME)) {
                WS_ERR_MSG = EACMSG_ERROR_MSG.EA_AC_NAME_NOT_MATCH;
                S000_ERR_MSG_PROC();
            } else {
                WS_ERR_MSG = EACMSG_ERROR_MSG.EA_AC_CANNOT_TRAN_OUT;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B200_C_CNTL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, EACUTRAN.AC_FLG);
        CEP.TRC(SCCGWA, EACUTRAN.AC_NME);
        CEP.TRC(SCCGWA, EACUTRAN.OPP_AC_NME);
        CEP.TRC(SCCGWA, EACUTRAN.CARD_NO);
        CEP.TRC(SCCGWA, EACUTRAN.OPP_AC);
        CEP.TRC(SCCGWA, EACUTRAN.OPP_BNK);
        CEP.TRC(SCCGWA, EACUTRAN.OPP_BNK_NME);
        CEP.TRC(SCCGWA, EACUTRAN.IO_FLG);
        CEP.TRC(SCCGWA, EACUTRAN.OPP_AC_NME);
        CEP.TRC(SCCGWA, EACUTRAN.RMK);
        if (EACUTRAN.AC_FLG == '2') {
            R_GET_EA_TRAN_PARM();
            WS_AC_CHK_FLG = EACTRAN.DATA_TXT.CON_FLG;
            WS_NME_CHK_FLG = EACTRAN.DATA_TXT.NME_FLG;
            WS_DC_FLG = 'C';
            R_CHK_WLST_PROC();
            if (WS_WLST_FLG != 'Y') {
                R_CHK_AC_NME_PROC();
            }
        } else {
            if (!EACUTRAN.AC_NME.equalsIgnoreCase(EACUTRAN.OPP_AC_NME)) {
                WS_ERR_MSG = EACMSG_ERROR_MSG.EA_AC_NAME_NOT_MATCH;
                S000_ERR_MSG_PROC();
            }
            EARACLNK.KEY.CARD_NO = EACUTRAN.CARD_NO;
            EARACLNK.KEY.CON_AC = EACUTRAN.OPP_AC;
            EARACLNK.KEY.CON_BNK = EACUTRAN.OPP_BNK;
            T000_READ_EATACLNK();
            if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
                EARACLNK.CON_NME = EACUTRAN.OPP_BNK_NME;
                EARACLNK.IO_FLG = EACUTRAN.IO_FLG;
                EARACLNK.AC_NAME = EACUTRAN.OPP_AC_NME;
                EARACLNK.CON_STS = 'B';
                EARACLNK.RMK = EACUTRAN.RMK;
                EARACLNK.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                EARACLNK.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                EARACLNK.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                EARACLNK.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                EARACLNK.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                T000_INSERT_EATACLNK();
            }
        }
    }
    public void R_CHK_WLST_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        CEP.TRC(SCCGWA, EACUTRAN.AC_FLG);
        CEP.TRC(SCCGWA, WS_DC_FLG);
        EARWLST.KEY.REQ_SYS = SCCGWA.COMM_AREA.CHNL;
        EARWLST.KEY.AC_FLG = EACUTRAN.AC_FLG;
        EARWLST.KEY.DC_FLG = WS_DC_FLG;
        T000_STARTBR_WLST();
        T000_READNEXT_WLST();
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && WS_WLST_FLG != 'Y') {
            if (EARWLST.KEY.LST_FLG == '1' 
                && EARWLST.KEY.LST_AC.equalsIgnoreCase(EACUTRAN.OPP_AC)) {
                WS_WLST_FLG = 'Y';
            }
            if (EARWLST.KEY.LST_AC == null) EARWLST.KEY.LST_AC = "";
            JIBS_tmp_int = EARWLST.KEY.LST_AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) EARWLST.KEY.LST_AC += " ";
            if (EARWLST.KEY.LST_FLG == '2' 
                && EARWLST.KEY.LST_AC.substring(0, 5).equalsIgnoreCase(EACUTRAN.MMO)) {
                WS_WLST_FLG = 'Y';
            }
            T000_READNEXT_WLST();
        }
        T000_ENDBR_WLST();
    }
    public void R_CHK_AC_NME_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, EACUTRAN.CARD_NO);
        CEP.TRC(SCCGWA, EACUTRAN.OPP_AC);
        CEP.TRC(SCCGWA, EACUTRAN.OPP_AC_NME);
        EARACLNK.KEY.CARD_NO = EACUTRAN.CARD_NO;
        T000_STARTBR_ACLNK();
        T000_READNEXT_ACLNK();
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            if (EARACLNK.KEY.CON_AC.equalsIgnoreCase(EACUTRAN.OPP_AC)) {
                WS_AC_PASS_FLG = 'Y';
            }
            if (EARACLNK.AC_NAME.equalsIgnoreCase(EACUTRAN.OPP_AC_NME)) {
                WS_NME_PASS_FLG = 'Y';
            }
            T000_READNEXT_ACLNK();
        }
        T000_ENDBR_ACLNK();
        if (WS_AC_CHK_FLG == 'Y' 
            && WS_AC_PASS_FLG != 'Y') {
            WS_ERR_MSG = EACMSG_ERROR_MSG.EA_BIND_AC_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
        if (WS_NME_CHK_FLG == 'Y' 
            && WS_NME_PASS_FLG != 'Y') {
            WS_ERR_MSG = EACMSG_ERROR_MSG.EA_AC_NAME_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
    }
    public void R_GET_EA_TRAN_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EACTRAN);
        EACTRAN.PARM_TYPE = "PEA02";
        EACTRAN.PARM_CODE = EACUTRAN.PROD_CODE;
        IBS.init(SCCGWA, BPCPRMR);
        BPCPRMR.DAT_PTR = EACTRAN;
        S000_CALL_BPZPRMR();
    }
    public void T000_STARTBR_WLST() throws IOException,SQLException,Exception {
        EATWLST_BR.rp = new DBParm();
        EATWLST_BR.rp.TableName = "EATWLST";
        EATWLST_BR.rp.eqWhere = "REQ_SYS,AC_FLG,DC_FLG";
        IBS.STARTBR(SCCGWA, EARWLST, EATWLST_BR);
    }
    public void T000_READNEXT_WLST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, EARWLST, this, EATWLST_BR);
    }
    public void T000_ENDBR_WLST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, EATWLST_BR);
    }
    public void T000_STARTBR_ACLNK() throws IOException,SQLException,Exception {
        EATACLNK_BR.rp = new DBParm();
        EATACLNK_BR.rp.TableName = "EATACLNK";
        EATACLNK_BR.rp.eqWhere = "CARD_NO";
        IBS.STARTBR(SCCGWA, EARACLNK, EATACLNK_BR);
    }
    public void T000_READNEXT_ACLNK() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, EARACLNK, this, EATACLNK_BR);
    }
    public void T000_ENDBR_ACLNK() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, EATACLNK_BR);
    }
    public void T000_READ_EATACLNK() throws IOException,SQLException,Exception {
        EATACLNK_RD = new DBParm();
        EATACLNK_RD.TableName = "EATACLNK";
        IBS.READ(SCCGWA, EARACLNK, EATACLNK_RD);
    }
    public void T000_INSERT_EATACLNK() throws IOException,SQLException,Exception {
        EATACLNK_RD = new DBParm();
        EATACLNK_RD.TableName = "EATACLNK";
        IBS.WRITE(SCCGWA, EARACLNK, EATACLNK_RD);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, SCCBINF);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
