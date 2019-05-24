package com.hisun.DD;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSBZMQ {
    brParm DDTZMQT_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DD110";
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    String WS_FR_AC = " ";
    int WS_STR_DATE = 0;
    int WS_END_DATE = 0;
    char WS_ZMQT_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DDCOZMQT DDCOZMQT = new DDCOZMQT();
    DDRCCY DDRCCY = new DDRCCY();
    DDRZMQT DDRZMQT = new DDRZMQT();
    SCCGWA SCCGWA;
    DDCSBZMQ DDCSBZMQ;
    public void MP(SCCGWA SCCGWA, DDCSBZMQ DDCSBZMQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSBZMQ = DDCSBZMQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSBZMQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHK_INPUT_DATA();
        if (pgmRtn) return;
        B200_BROWS_TRANS_REC();
        if (pgmRtn) return;
    }
    public void B100_CHK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSBZMQ.FUNC);
        CEP.TRC(SCCGWA, DDCSBZMQ.OUT_BR);
        CEP.TRC(SCCGWA, DDCSBZMQ.IN_BR);
        CEP.TRC(SCCGWA, DDCSBZMQ.STR_DATE);
        CEP.TRC(SCCGWA, DDCSBZMQ.END_DATE);
        CEP.TRC(SCCGWA, DDCSBZMQ.STS);
        CEP.TRC(SCCGWA, DDCSBZMQ.IN_JRN);
        CEP.TRC(SCCGWA, DDCSBZMQ.OUT_JRN);
        if (DDCSBZMQ.FUNC == ' ') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_FUNC_M_INPUT);
        }
        if (DDCSBZMQ.FUNC != '1' 
            && DDCSBZMQ.FUNC != '2' 
            && DDCSBZMQ.FUNC != '3' 
            && DDCSBZMQ.FUNC != '4') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_FUNC_INVALID);
        }
        if (DDCSBZMQ.FUNC == '1' 
            && DDCSBZMQ.OUT_BR == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_OUT_BR_M_INPUT);
        }
        if (DDCSBZMQ.FUNC == '2' 
            && DDCSBZMQ.IN_BR == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_IN_BR_M_INPUT);
        }
        if (DDCSBZMQ.FUNC == '3' 
            && DDCSBZMQ.IN_JRN == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_IN_JRN_M_INPUT);
        }
        if (DDCSBZMQ.FUNC == '4' 
            && DDCSBZMQ.OUT_JRN == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_OUT_JRN_M_INPUT);
        }
        if (DDCSBZMQ.STR_DATE == ' ') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_STRDATE_M_INPUT);
        }
        if (DDCSBZMQ.END_DATE == ' ') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ENDDATE_M_INPUT);
        }
    }
    public void B200_BROWS_TRANS_REC() throws IOException,SQLException,Exception {
        WS_STR_DATE = DDCSBZMQ.STR_DATE;
        WS_END_DATE = DDCSBZMQ.END_DATE;
        if (DDCSBZMQ.FUNC == '1') {
            B010_BRWS_DDTZMQT_BY_OUTBR();
            if (pgmRtn) return;
        } else if (DDCSBZMQ.FUNC == '2') {
            B020_BRWS_DDTZMQT_BY_INBR();
            if (pgmRtn) return;
        } else if (DDCSBZMQ.FUNC == '3') {
            B030_BRWS_DDTZMQT_BY_INJRN();
            if (pgmRtn) return;
        } else if (DDCSBZMQ.FUNC == '4') {
            B040_BRWS_DDTZMQT_BY_OUTJRN();
            if (pgmRtn) return;
        }
    }
    public void B010_BRWS_DDTZMQT_BY_OUTBR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSBZMQ.STS);
        CEP.TRC(SCCGWA, WS_STR_DATE);
        CEP.TRC(SCCGWA, WS_END_DATE);
        if (DDCSBZMQ.STS != ' ') {
            IBS.init(SCCGWA, DDRZMQT);
            DDRZMQT.FRM_BR = DDCSBZMQ.OUT_BR;
            DDRZMQT.STS = DDCSBZMQ.STS;
            R000_STARTBR_BY_OUTBR_STS();
            if (pgmRtn) return;
            R000_READNEXT_DDTZMQT();
            if (pgmRtn) return;
            B300_BEGIN_MPAGE_OUTPUT();
            if (pgmRtn) return;
            while (WS_ZMQT_FLG != 'Y') {
                B310_OUTPUT_DATA();
                if (pgmRtn) return;
                R000_READNEXT_DDTZMQT();
                if (pgmRtn) return;
            }
            R000_ENDBR_DDTZMQT();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, DDRZMQT);
            DDRZMQT.FRM_BR = DDCSBZMQ.OUT_BR;
            R000_STARTBR_BY_OUTBR();
            if (pgmRtn) return;
            R000_READNEXT_DDTZMQT();
            if (pgmRtn) return;
            B300_BEGIN_MPAGE_OUTPUT();
            if (pgmRtn) return;
            while (WS_ZMQT_FLG != 'Y') {
                B310_OUTPUT_DATA();
                if (pgmRtn) return;
                R000_READNEXT_DDTZMQT();
                if (pgmRtn) return;
            }
            R000_ENDBR_DDTZMQT();
            if (pgmRtn) return;
        }
    }
    public void B020_BRWS_DDTZMQT_BY_INBR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSBZMQ.STS);
        CEP.TRC(SCCGWA, WS_STR_DATE);
        CEP.TRC(SCCGWA, WS_END_DATE);
        if (DDCSBZMQ.STS != ' ') {
            IBS.init(SCCGWA, DDRZMQT);
            DDRZMQT.TO_BR = DDCSBZMQ.IN_BR;
            DDRZMQT.STS = DDCSBZMQ.STS;
            R000_STARTBR_BY_INBR_STS();
            if (pgmRtn) return;
            R000_READNEXT_DDTZMQT();
            if (pgmRtn) return;
            B300_BEGIN_MPAGE_OUTPUT();
            if (pgmRtn) return;
            while (WS_ZMQT_FLG != 'Y') {
                B310_OUTPUT_DATA();
                if (pgmRtn) return;
                R000_READNEXT_DDTZMQT();
                if (pgmRtn) return;
            }
            R000_ENDBR_DDTZMQT();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, DDRZMQT);
            DDRZMQT.TO_BR = DDCSBZMQ.IN_BR;
            R000_STARTBR_BY_INBR();
            if (pgmRtn) return;
            R000_READNEXT_DDTZMQT();
            if (pgmRtn) return;
            B300_BEGIN_MPAGE_OUTPUT();
            if (pgmRtn) return;
            while (WS_ZMQT_FLG != 'Y') {
                B310_OUTPUT_DATA();
                if (pgmRtn) return;
                R000_READNEXT_DDTZMQT();
                if (pgmRtn) return;
            }
            R000_ENDBR_DDTZMQT();
            if (pgmRtn) return;
        }
    }
    public void B030_BRWS_DDTZMQT_BY_INJRN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSBZMQ.STS);
        CEP.TRC(SCCGWA, WS_STR_DATE);
        CEP.TRC(SCCGWA, WS_END_DATE);
        if (DDCSBZMQ.STS != ' ') {
            IBS.init(SCCGWA, DDRZMQT);
            DDRZMQT.IN_JRN = DDCSBZMQ.IN_JRN;
            DDRZMQT.STS = DDCSBZMQ.STS;
            R000_STARTBR_BY_INJRN_STS();
            if (pgmRtn) return;
            R000_READNEXT_DDTZMQT();
            if (pgmRtn) return;
            B300_BEGIN_MPAGE_OUTPUT();
            if (pgmRtn) return;
            while (WS_ZMQT_FLG != 'Y') {
                B310_OUTPUT_DATA();
                if (pgmRtn) return;
                R000_READNEXT_DDTZMQT();
                if (pgmRtn) return;
            }
            R000_ENDBR_DDTZMQT();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, DDRZMQT);
            DDRZMQT.IN_JRN = DDCSBZMQ.IN_JRN;
            R000_STARTBR_BY_INJRN();
            if (pgmRtn) return;
            R000_READNEXT_DDTZMQT();
            if (pgmRtn) return;
            B300_BEGIN_MPAGE_OUTPUT();
            if (pgmRtn) return;
            while (WS_ZMQT_FLG != 'Y') {
                B310_OUTPUT_DATA();
                if (pgmRtn) return;
                R000_READNEXT_DDTZMQT();
                if (pgmRtn) return;
            }
            R000_ENDBR_DDTZMQT();
            if (pgmRtn) return;
        }
    }
    public void B040_BRWS_DDTZMQT_BY_OUTJRN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSBZMQ.STS);
        CEP.TRC(SCCGWA, WS_STR_DATE);
        CEP.TRC(SCCGWA, WS_END_DATE);
        if (DDCSBZMQ.STS != ' ') {
            IBS.init(SCCGWA, DDRZMQT);
            DDRZMQT.KEY.OUT_JRN = DDCSBZMQ.OUT_JRN;
            DDRZMQT.STS = DDCSBZMQ.STS;
            R000_STARTBR_BY_OUTJRN_STS();
            if (pgmRtn) return;
            R000_READNEXT_DDTZMQT();
            if (pgmRtn) return;
            B300_BEGIN_MPAGE_OUTPUT();
            if (pgmRtn) return;
            while (WS_ZMQT_FLG != 'Y') {
                B310_OUTPUT_DATA();
                if (pgmRtn) return;
                R000_READNEXT_DDTZMQT();
                if (pgmRtn) return;
            }
            R000_ENDBR_DDTZMQT();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, DDRZMQT);
            DDRZMQT.KEY.OUT_JRN = DDCSBZMQ.OUT_JRN;
            R000_STARTBR_BY_OUTJRN();
            if (pgmRtn) return;
            R000_READNEXT_DDTZMQT();
            if (pgmRtn) return;
            B300_BEGIN_MPAGE_OUTPUT();
            if (pgmRtn) return;
            while (WS_ZMQT_FLG != 'Y') {
                B310_OUTPUT_DATA();
                if (pgmRtn) return;
                R000_READNEXT_DDTZMQT();
                if (pgmRtn) return;
            }
            R000_ENDBR_DDTZMQT();
            if (pgmRtn) return;
        }
    }
    public void R000_STARTBR_BY_OUTJRN_STS() throws IOException,SQLException,Exception {
        DDTZMQT_BR.rp = new DBParm();
        DDTZMQT_BR.rp.TableName = "DDTZMQT";
        DDTZMQT_BR.rp.where = "OUT_JRN = :DDRZMQT.KEY.OUT_JRN "
            + "AND STS = :DDRZMQT.STS "
            + "AND OUT_DATE > :WS_STR_DATE "
            + "AND OUT_DATE < :WS_END_DATE";
        IBS.STARTBR(SCCGWA, DDRZMQT, this, DDTZMQT_BR);
    }
    CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        WS_ZMQT_FLG = 'N';
    }
    if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        WS_ZMQT_FLG = 'Y';
    }
    public void R000_STARTBR_BY_OUTJRN() throws IOException,SQLException,Exception {
        DDTZMQT_BR.rp = new DBParm();
        DDTZMQT_BR.rp.TableName = "DDTZMQT";
        DDTZMQT_BR.rp.where = "OUT_JRN = :DDRZMQT.KEY.OUT_JRN "
            + "AND OUT_DATE > :WS_STR_DATE "
            + "AND OUT_DATE < :WS_END_DATE";
        IBS.STARTBR(SCCGWA, DDRZMQT, this, DDTZMQT_BR);
    }
    CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        WS_ZMQT_FLG = 'N';
    }
    if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        WS_ZMQT_FLG = 'Y';
    }
    public void R000_STARTBR_BY_INJRN_STS() throws IOException,SQLException,Exception {
        DDTZMQT_BR.rp = new DBParm();
        DDTZMQT_BR.rp.TableName = "DDTZMQT";
        DDTZMQT_BR.rp.where = "IN_JRN = :DDRZMQT.IN_JRN "
            + "AND STS = :DDRZMQT.STS "
            + "AND OUT_DATE > :WS_STR_DATE "
            + "AND OUT_DATE < :WS_END_DATE";
        IBS.STARTBR(SCCGWA, DDRZMQT, this, DDTZMQT_BR);
    }
    CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        WS_ZMQT_FLG = 'N';
    }
    if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        WS_ZMQT_FLG = 'Y';
    }
    public void R000_STARTBR_BY_INJRN() throws IOException,SQLException,Exception {
        DDTZMQT_BR.rp = new DBParm();
        DDTZMQT_BR.rp.TableName = "DDTZMQT";
        DDTZMQT_BR.rp.where = "IN_JRN = :DDRZMQT.IN_JRN "
            + "AND OUT_DATE > :WS_STR_DATE "
            + "AND OUT_DATE < :WS_END_DATE";
        IBS.STARTBR(SCCGWA, DDRZMQT, this, DDTZMQT_BR);
    }
    CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        WS_ZMQT_FLG = 'N';
    }
    if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        WS_ZMQT_FLG = 'Y';
    }
    public void R000_STARTBR_BY_OUTBR_STS() throws IOException,SQLException,Exception {
        DDTZMQT_BR.rp = new DBParm();
        DDTZMQT_BR.rp.TableName = "DDTZMQT";
        DDTZMQT_BR.rp.where = "FRM_BR = :DDRZMQT.FRM_BR "
            + "AND STS = :DDRZMQT.STS "
            + "AND OUT_DATE >= :WS_STR_DATE "
            + "AND OUT_DATE <= :WS_END_DATE";
        IBS.STARTBR(SCCGWA, DDRZMQT, this, DDTZMQT_BR);
    }
    CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        WS_ZMQT_FLG = 'N';
    }
    if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        WS_ZMQT_FLG = 'Y';
    }
    public void R000_STARTBR_BY_INBR_STS() throws IOException,SQLException,Exception {
        DDTZMQT_BR.rp = new DBParm();
        DDTZMQT_BR.rp.TableName = "DDTZMQT";
        DDTZMQT_BR.rp.where = "TO_BR = :DDRZMQT.TO_BR "
            + "AND STS = :DDRZMQT.STS "
            + "AND OUT_DATE >= :WS_STR_DATE "
            + "AND OUT_DATE <= :WS_END_DATE";
        IBS.STARTBR(SCCGWA, DDRZMQT, this, DDTZMQT_BR);
    }
    CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        WS_ZMQT_FLG = 'N';
    }
    if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        WS_ZMQT_FLG = 'Y';
    }
    public void R000_STARTBR_BY_INBR() throws IOException,SQLException,Exception {
        DDTZMQT_BR.rp = new DBParm();
        DDTZMQT_BR.rp.TableName = "DDTZMQT";
        DDTZMQT_BR.rp.where = "TO_BR = :DDRZMQT.TO_BR "
            + "AND OUT_DATE >= :WS_STR_DATE "
            + "AND OUT_DATE <= :WS_END_DATE";
        IBS.STARTBR(SCCGWA, DDRZMQT, this, DDTZMQT_BR);
    }
    CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        WS_ZMQT_FLG = 'N';
    }
    if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        WS_ZMQT_FLG = 'Y';
    }
    public void R000_STARTBR_BY_OUTBR() throws IOException,SQLException,Exception {
        DDTZMQT_BR.rp = new DBParm();
        DDTZMQT_BR.rp.TableName = "DDTZMQT";
        DDTZMQT_BR.rp.where = "FRM_BR = :DDRZMQT.FRM_BR "
            + "AND OUT_DATE >= :WS_STR_DATE "
            + "AND OUT_DATE <= :WS_END_DATE";
        IBS.STARTBR(SCCGWA, DDRZMQT, this, DDTZMQT_BR);
    }
    CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        WS_ZMQT_FLG = 'N';
    }
    if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        WS_ZMQT_FLG = 'Y';
    }
    public void R000_READNEXT_DDTZMQT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRZMQT, this, DDTZMQT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ZMQT_FLG = 'N';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ZMQT_FLG = 'Y';
        }
    }
    public void R000_ENDBR_DDTZMQT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTZMQT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ZMQT_FLG = 'N';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ZMQT_FLG = 'Y';
        }
    }
    public void B300_BEGIN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 971;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B310_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOZMQT);
        DDCOZMQT.FRM_BR = DDRZMQT.FRM_BR;
        DDCOZMQT.TO_BR = DDRZMQT.TO_BR;
        DDCOZMQT.STS = DDRZMQT.STS;
        DDCOZMQT.OUT_DATE = DDRZMQT.KEY.OUT_DATE;
        DDCOZMQT.OUT_JRN = DDRZMQT.KEY.OUT_JRN;
        DDCOZMQT.IN_DATE = DDRZMQT.IN_DATE;
        DDCOZMQT.IN_JRN = DDRZMQT.IN_JRN;
        DDCOZMQT.FRM_AC = DDRZMQT.FRM_AC;
        DDCOZMQT.FRM_ACNM = DDRZMQT.FRM_AC_NM;
        DDCOZMQT.FRM_CCY = DDRZMQT.FRM_CCY;
        DDCOZMQT.FRM_CCY_TYP = DDRZMQT.FRM_CCY_TYP;
        DDCOZMQT.TR_AMT = DDRZMQT.OUT_AMT;
        DDCOZMQT.TO_AC = DDRZMQT.TO_AC;
        DDCOZMQT.TO_ACNM = DDRZMQT.TO_AC_NM;
        DDCOZMQT.TO_CCY = DDRZMQT.TO_CCY;
        DDCOZMQT.TO_CCY_TYP = DDRZMQT.TO_CCY_TYP;
        DDCOZMQT.TR_RSN = DDRZMQT.TR_RSN;
        DDCOZMQT.TR_DESC = DDRZMQT.TR_DESC;
        DDCOZMQT.TR_RMK = DDRZMQT.TR_RMK;
        CEP.TRC(SCCGWA, DDCOZMQT.FRM_BR);
        CEP.TRC(SCCGWA, DDCOZMQT.TO_BR);
        CEP.TRC(SCCGWA, DDCOZMQT.STS);
        CEP.TRC(SCCGWA, DDCOZMQT.OUT_DATE);
        CEP.TRC(SCCGWA, DDCOZMQT.OUT_JRN);
        CEP.TRC(SCCGWA, DDCOZMQT.IN_DATE);
        CEP.TRC(SCCGWA, DDCOZMQT.IN_JRN);
        CEP.TRC(SCCGWA, DDCOZMQT.FRM_AC);
        CEP.TRC(SCCGWA, DDCOZMQT.FRM_ACNM);
        CEP.TRC(SCCGWA, DDCOZMQT.FRM_CCY);
        CEP.TRC(SCCGWA, DDCOZMQT.FRM_CCY_TYP);
        CEP.TRC(SCCGWA, DDCOZMQT.TR_AMT);
        CEP.TRC(SCCGWA, DDCOZMQT.TO_AC);
        CEP.TRC(SCCGWA, DDCOZMQT.TO_ACNM);
        CEP.TRC(SCCGWA, DDCOZMQT.TO_CCY);
        CEP.TRC(SCCGWA, DDCOZMQT.TO_CCY_TYP);
        CEP.TRC(SCCGWA, DDCOZMQT.TR_RSN);
        CEP.TRC(SCCGWA, DDCOZMQT.TR_DESC);
        CEP.TRC(SCCGWA, DDCOZMQT.TR_RMK);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, DDCOZMQT);
        SCCMPAG.DATA_LEN = 971;
        B_MPAG();
        if (pgmRtn) return;
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
