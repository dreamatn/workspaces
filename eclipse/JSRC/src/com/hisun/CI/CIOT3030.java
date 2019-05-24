package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT3030 {
    int JIBS_tmp_int;
    DBParm CITTPINF_RD;
    brParm CITTPINF_BR = new brParm();
    String K_PRDP_TYP = "PRDPR";
    String K_IRUL_TYP = "TIRUL";
    String K_AP_MMO = "TD";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String PGM_SCSSCKDT = "SCSSCKDT";
    short WS_I = 0;
    short WS_VAL1 = 0;
    short WS_VAL2 = 0;
    char WS_TPINF_FLG = ' ';
    int WS_SEQ = 0;
    short WS_LAST_CON_SEQ = 0;
    short WS_LAST_CON_SEQ_1 = 0;
    short YY = 0;
    short MM = 0;
    short DD = 0;
    short R = 0;
    char WS_TPINF_FLG = ' ';
    char WS_CON_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCENPSW SCCENPSW = new SCCENPSW();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCBINF SCCBINF = new SCCBINF();
    CIRTYP CIRTYP = new CIRTYP();
    CIRCMPR CIRCMPR = new CIRCMPR();
    CIRTPINF CIRTPINF = new CIRTPINF();
    CIRTPINF CIROPINF = new CIRTPINF();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    CIB3030_AWA_3030 CIB3030_AWA_3030;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT3030 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB3030_AWA_3030>");
        CIB3030_AWA_3030 = (CIB3030_AWA_3030) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        IBS.init(SCCGWA, CIRTPINF);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        CEP.TRC(SCCGWA, CIB3030_AWA_3030.FUNC);
        CEP.TRC(SCCGWA, CIB3030_AWA_3030.TYPE);
        CEP.TRC(SCCGWA, CIB3030_AWA_3030.TIMES[1-1].CON_SEQ);
        CEP.TRC(SCCGWA, CIB3030_AWA_3030.TIMES[1-1].CON_TYP);
        CEP.TRC(SCCGWA, CIB3030_AWA_3030.TIMES[1-1].VAL);
        CEP.TRC(SCCGWA, CIB3030_AWA_3030.TIMES[1-1].VAL_COND);
        CEP.TRC(SCCGWA, CIB3030_AWA_3030.TIMES[2-1].CON_SEQ);
        CEP.TRC(SCCGWA, CIB3030_AWA_3030.TIMES[2-1].CON_TYP);
        CEP.TRC(SCCGWA, CIB3030_AWA_3030.TIMES[2-1].VAL);
        CEP.TRC(SCCGWA, CIB3030_AWA_3030.TIMES[2-1].VAL_COND);
        CEP.TRC(SCCGWA, CIB3030_AWA_3030.TIMES[11-1].CON_SEQ);
        CEP.TRC(SCCGWA, CIB3030_AWA_3030.TIMES[11-1].CON_TYP);
        CEP.TRC(SCCGWA, CIB3030_AWA_3030.TIMES[11-1].VAL);
        CEP.TRC(SCCGWA, CIB3030_AWA_3030.TIMES[11-1].VAL_COND);
        CEP.TRC(SCCGWA, CIB3030_AWA_3030.TIMES[21-1].CON_SEQ);
        CEP.TRC(SCCGWA, CIB3030_AWA_3030.TIMES[21-1].CON_TYP);
        CEP.TRC(SCCGWA, CIB3030_AWA_3030.TIMES[21-1].VAL);
        CEP.TRC(SCCGWA, CIB3030_AWA_3030.TIMES[21-1].VAL_COND);
        CEP.TRC(SCCGWA, CIB3030_AWA_3030.TIMES[31-1].CON_SEQ);
        CEP.TRC(SCCGWA, CIB3030_AWA_3030.TIMES[31-1].CON_TYP);
        CEP.TRC(SCCGWA, CIB3030_AWA_3030.TIMES[31-1].VAL);
        CEP.TRC(SCCGWA, CIB3030_AWA_3030.TIMES[31-1].VAL_COND);
        CEP.TRC(SCCGWA, CIB3030_AWA_3030.TIMES[41-1].CON_SEQ);
        CEP.TRC(SCCGWA, CIB3030_AWA_3030.TIMES[41-1].CON_TYP);
        CEP.TRC(SCCGWA, CIB3030_AWA_3030.TIMES[41-1].VAL);
        CEP.TRC(SCCGWA, CIB3030_AWA_3030.TIMES[41-1].VAL_COND);
        B100_CHECK_INPUT();
        if (CIB3030_AWA_3030.FUNC == '0') {
            B210_ADD_CITTPINF_INFO();
        } else if (CIB3030_AWA_3030.FUNC == '1') {
            B220_UPD_CITTPINF_INFO();
        } else if (CIB3030_AWA_3030.FUNC == '2') {
            B230_DEL_CITTPINF_INFO();
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_ERROR);
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (CIB3030_AWA_3030.TYPE.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_KIROKU_NOTFND);
        }
    }
    public void B210_ADD_CITTPINF_INFO() throws IOException,SQLException,Exception {
        CIRTPINF.KEY.TYPE = CIB3030_AWA_3030.TYPE;
        T000_READ_CITTPINF_FIRST();
        IBS.init(SCCGWA, CIRTPINF);
        CIRTPINF.KEY.TYPE = CIB3030_AWA_3030.TYPE;
        for (WS_I = 1; WS_I <= 50; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, CIB3030_AWA_3030.TIMES[WS_I-1].CON_TYP);
            if (CIB3030_AWA_3030.TIMES[WS_I-1].CON_TYP != 0) {
                if (WS_CON_FLG == 'C' 
                    && CIB3030_AWA_3030.TIMES[WS_I-1].CON_TYP == 16 
                    && CIB3030_AWA_3030.TIMES[WS_I-1].CON_SEQ == WS_LAST_CON_SEQ) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_AC_ONCE);
                }
                if (WS_CON_FLG == 'A' 
                    && CIB3030_AWA_3030.TIMES[WS_I-1].CON_TYP == 25 
                    && CIB3030_AWA_3030.TIMES[WS_I-1].CON_SEQ == WS_LAST_CON_SEQ_1) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_AC_ONCE);
                }
                if (CIB3030_AWA_3030.TIMES[WS_I-1].CON_TYP == 16) {
                    WS_CON_FLG = 'C';
                    WS_LAST_CON_SEQ = CIB3030_AWA_3030.TIMES[WS_I-1].CON_SEQ;
                }
                if (CIB3030_AWA_3030.TIMES[WS_I-1].CON_TYP == 25) {
                    WS_CON_FLG = 'A';
                    WS_LAST_CON_SEQ_1 = CIB3030_AWA_3030.TIMES[WS_I-1].CON_SEQ;
                }
                CEP.TRC(SCCGWA, CIB3030_AWA_3030.TIMES[WS_I-1].CON_SEQ);
                CIRTPINF.KEY.SEQ = CIB3030_AWA_3030.TIMES[WS_I-1].CON_SEQ;
                CEP.TRC(SCCGWA, CIRTPINF.KEY.SEQ);
                CIRTPINF.KEY.CON_TYP = "" + CIB3030_AWA_3030.TIMES[WS_I-1].CON_TYP;
                JIBS_tmp_int = CIRTPINF.KEY.CON_TYP.length();
                for (int i=0;i<2-JIBS_tmp_int;i++) CIRTPINF.KEY.CON_TYP = "0" + CIRTPINF.KEY.CON_TYP;
                CIRTPINF.KEY.VAL = CIB3030_AWA_3030.TIMES[WS_I-1].VAL;
                CIRTPINF.VAL_COND = CIB3030_AWA_3030.TIMES[WS_I-1].VAL_COND;
                CIRTPINF.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                CIRTPINF.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                if (CIB3030_AWA_3030.FUNC == '0') {
                    CIRTPINF.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                    CIRTPINF.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                }
                T000_INSERT_CITTPINF();
            }
        }
    }
    public void B220_UPD_CITTPINF_INFO() throws IOException,SQLException,Exception {
        B230_DEL_CITTPINF_INFO();
        B210_ADD_CITTPINF_INFO();
    }
    public void B230_DEL_CITTPINF_INFO() throws IOException,SQLException,Exception {
        CIRTPINF.KEY.TYPE = CIB3030_AWA_3030.TYPE;
        T000_STARTBR_CITTPINF();
        T000_READNEXT_CITTPINF();
        while (WS_TPINF_FLG != 'Y') {
            T000_DELETE_CITTPINF();
            T000_READNEXT_CITTPINF();
        }
        T000_ENDBR_CITTPINF();
    }
    public void T000_INSERT_CITTPINF() throws IOException,SQLException,Exception {
        CITTPINF_RD = new DBParm();
        CITTPINF_RD.TableName = "CITTPINF";
        IBS.WRITE(SCCGWA, CIRTPINF, CITTPINF_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INSERT_ERROR);
        }
    }
    public void T000_STARTBR_CITTPINF() throws IOException,SQLException,Exception {
        CITTPINF_BR.rp = new DBParm();
        CITTPINF_BR.rp.TableName = "CITTPINF";
        CITTPINF_BR.rp.where = "TYPE = :CIRTPINF.KEY.TYPE";
        CITTPINF_BR.rp.order = "SEQ";
        IBS.STARTBR(SCCGWA, CIRTPINF, this, CITTPINF_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READNEXT_CITTPINF() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRTPINF, this, CITTPINF_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TPINF_FLG = 'N';
            CIROPINF.KEY.TYPE = CIRTPINF.KEY.TYPE;
            CIROPINF.KEY.SEQ = CIRTPINF.KEY.SEQ;
        } else {
            WS_TPINF_FLG = 'Y';
        }
    }
    public void T000_ENDBR_CITTPINF() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITTPINF_BR);
    }
    public void T000_DELETE_CITTPINF() throws IOException,SQLException,Exception {
        CITTPINF_RD = new DBParm();
        CITTPINF_RD.TableName = "CITTPINF";
        CITTPINF_RD.where = "TYPE = :CIROPINF.KEY.TYPE "
            + "AND SEQ = :CIROPINF.KEY.SEQ";
        IBS.DELETE(SCCGWA, CIRTPINF, this, CITTPINF_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READ_CITTPINF_FIRST() throws IOException,SQLException,Exception {
        CITTPINF_RD = new DBParm();
        CITTPINF_RD.TableName = "CITTPINF";
        CITTPINF_RD.where = "TYPE = :CIRTPINF.KEY.TYPE";
        CITTPINF_RD.fst = true;
        CITTPINF_RD.order = "SEQ DESC";
        IBS.READ(SCCGWA, CIRTPINF, this, CITTPINF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TPINF_FLG = 'Y';
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
