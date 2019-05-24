package com.hisun.EA;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class EAZSBINQ {
    brParm EATACLNK_BR = new brParm();
    String WS_CARD_NO = " ";
    char WS_CON_STS = ' ';
    String WS_CON_NO = " ";
    String K_AP_MMO = "EA";
    String K_SYS_ERR = "SC6001";
    String K_OUTPUT_FMT = "EA890";
    String WS_ERR_MSG = " ";
    short WS_RC = 0;
    short WS_RC_DISP = 0;
    char WS_ACLNK_FLG = ' ';
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
    EACO890 EACO890 = new EACO890();
    EARACLNK EARACLNK = new EARACLNK();
    SCCGWA SCCGWA;
    EACSBINQ EACSBINQ;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, EACSBINQ EACSBINQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.EACSBINQ = EACSBINQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "OUTCDINT");
        CEP.TRC(SCCGWA, "EAZSBINQ return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, EACO890);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, EACSBINQ.CARD_NO);
        CEP.TRC(SCCGWA, EACSBINQ.BEG_SEQ);
        CEP.TRC(SCCGWA, EACSBINQ.I_AC);
        if (EACSBINQ.I_AC.trim().length() > 0) {
            B100_I_AC_INQURY();
        } else {
            if (EACSBINQ.CARD_NO.trim().length() == 0) {
                CEP.ERR(SCCGWA, EACMSG_ERROR_MSG.EA_INPUT_ERROR);
            }
            EARACLNK.KEY.CARD_NO = EACSBINQ.CARD_NO;
            EACO890.CARD_NO = EACSBINQ.CARD_NO;
            T000_STARTBR_ACLNK();
            T000_READNEXT_ACLNK();
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
                && WS_ACLNK_FLG != 'Y') {
                WS_SEQ += 1;
                if (WS_SEQ > EACSBINQ.BEG_SEQ) {
                    WS_I += 1;
                    EACO890.AC_ARRAY[WS_I-1].CON_AC = EARACLNK.KEY.CON_AC;
                    EACSBINQ.AC_ARRAY[WS_I-1].CON_AC = EARACLNK.KEY.CON_AC;
                    EACO890.AC_ARRAY[WS_I-1].IO_FLG = EARACLNK.IO_FLG;
                    EACSBINQ.AC_ARRAY[WS_I-1].IO_FLG = EARACLNK.IO_FLG;
                    EACO890.AC_ARRAY[WS_I-1].CON_BNK = EARACLNK.KEY.CON_BNK;
                    EACSBINQ.AC_ARRAY[WS_I-1].CON_BNK = EARACLNK.KEY.CON_BNK;
                    EACO890.AC_ARRAY[WS_I-1].CON_NME = EARACLNK.CON_NME;
                    EACSBINQ.AC_ARRAY[WS_I-1].CON_NME = EARACLNK.CON_NME;
                    EACO890.AC_ARRAY[WS_I-1].AC_NM = EARACLNK.AC_NAME;
                    EACSBINQ.AC_ARRAY[WS_I-1].AC_NM = EARACLNK.AC_NAME;
                    EACO890.AC_ARRAY[WS_I-1].CON_STS = EARACLNK.CON_STS;
                    EACSBINQ.AC_ARRAY[WS_I-1].CON_STS = EARACLNK.CON_STS;
                    EACO890.AC_ARRAY[WS_I-1].CRT_DTE = EARACLNK.CRT_DATE;
                    EACSBINQ.AC_ARRAY[WS_I-1].CRT_DTE = EARACLNK.CRT_DATE;
                    if (EARACLNK.CON_STS == 'C') {
                        EACO890.AC_ARRAY[WS_I-1].CLO_DTE = EARACLNK.UPDTBL_DATE;
                        EACSBINQ.AC_ARRAY[WS_I-1].CLO_DTE = EARACLNK.UPDTBL_DATE;
                    }
                }
                if (WS_I >= 5) {
                    WS_ACLNK_FLG = 'Y';
                } else {
                    T000_READNEXT_ACLNK();
                }
            }
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                EACO890.END_FLG = 'Y';
                EACSBINQ.END_FLG = 'Y';
            }
            EACO890.BEG_SEQ = WS_SEQ;
            T000_ENDBR_ACLNK();
        }
        R_OUTPUT_PROC();
    }
    public void B100_I_AC_INQURY() throws IOException,SQLException,Exception {
        WS_SEQ = 0;
        EARACLNK.KEY.CON_AC = EACSBINQ.I_AC;
        T000_STARTBR_ACLNK_I_AC();
        T000_READNEXT_ACLNK();
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && WS_ACLNK_FLG != 'Y') {
            WS_SEQ += 1;
            EACO890.CARD_NO = EACSBINQ.I_AC;
            if (WS_SEQ > EACSBINQ.BEG_SEQ) {
                WS_I += 1;
                EACO890.AC_ARRAY[WS_I-1].CON_AC = EARACLNK.KEY.CARD_NO;
                EACSBINQ.AC_ARRAY[WS_I-1].CON_AC = EARACLNK.KEY.CARD_NO;
                EACO890.AC_ARRAY[WS_I-1].IO_FLG = EARACLNK.IO_FLG;
                EACSBINQ.AC_ARRAY[WS_I-1].IO_FLG = EARACLNK.IO_FLG;
                EACO890.AC_ARRAY[WS_I-1].CON_BNK = EARACLNK.KEY.CON_BNK;
                EACSBINQ.AC_ARRAY[WS_I-1].CON_BNK = EARACLNK.KEY.CON_BNK;
                EACO890.AC_ARRAY[WS_I-1].CON_NME = EARACLNK.CON_NME;
                EACSBINQ.AC_ARRAY[WS_I-1].CON_NME = EARACLNK.CON_NME;
                EACO890.AC_ARRAY[WS_I-1].AC_NM = EARACLNK.AC_NAME;
                EACSBINQ.AC_ARRAY[WS_I-1].AC_NM = EARACLNK.AC_NAME;
                EACO890.AC_ARRAY[WS_I-1].CON_STS = EARACLNK.CON_STS;
                EACSBINQ.AC_ARRAY[WS_I-1].CON_STS = EARACLNK.CON_STS;
                EACO890.AC_ARRAY[WS_I-1].CRT_DTE = EARACLNK.CRT_DATE;
                EACSBINQ.AC_ARRAY[WS_I-1].CRT_DTE = EARACLNK.CRT_DATE;
                if (EARACLNK.CON_STS == 'C') {
                    EACO890.AC_ARRAY[WS_I-1].CLO_DTE = EARACLNK.UPDTBL_DATE;
                    EACSBINQ.AC_ARRAY[WS_I-1].CLO_DTE = EARACLNK.UPDTBL_DATE;
                }
            }
            if (WS_I >= 5) {
                WS_ACLNK_FLG = 'Y';
            } else {
                T000_READNEXT_ACLNK();
            }
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            EACO890.END_FLG = 'Y';
            EACSBINQ.END_FLG = 'Y';
        }
        EACO890.BEG_SEQ = WS_SEQ;
        EACSBINQ.END_SEQ = WS_SEQ;
        CEP.TRC(SCCGWA, EACSBINQ.END_SEQ);
        T000_ENDBR_ACLNK();
    }
    public void R_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_LEN = 2315;
        SCCFMT.DATA_PTR = EACO890;
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        CEP.TRC(SCCGWA, EACO890);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_STARTBR_ACLNK() throws IOException,SQLException,Exception {
        WS_CARD_NO = EACSBINQ.CARD_NO;
        WS_CON_STS = 'C';
        EATACLNK_BR.rp = new DBParm();
        EATACLNK_BR.rp.TableName = "EATACLNK";
        EATACLNK_BR.rp.where = "CARD_NO = :WS_CARD_NO "
            + "AND CON_STS < > :WS_CON_STS";
        IBS.STARTBR(SCCGWA, EARACLNK, this, EATACLNK_BR);
    }
    public void T000_STARTBR_ACLNK_I_AC() throws IOException,SQLException,Exception {
        WS_CON_NO = EACSBINQ.I_AC;
        WS_CON_STS = 'C';
        EATACLNK_BR.rp = new DBParm();
        EATACLNK_BR.rp.TableName = "EATACLNK";
        EATACLNK_BR.rp.where = "CON_AC = :WS_CON_NO "
            + "AND CON_STS < > :WS_CON_STS";
        IBS.STARTBR(SCCGWA, EARACLNK, this, EATACLNK_BR);
    }
    public void T000_READNEXT_ACLNK() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, EARACLNK, this, EATACLNK_BR);
    }
    public void T000_ENDBR_ACLNK() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, EATACLNK_BR);
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
