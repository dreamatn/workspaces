package com.hisun.CI;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZQUINF {
    int JIBS_tmp_int;
    DBParm CITTYP_RD;
    brParm CITHSTOT_BR = new brParm();
    DBParm CITDTL_RD;
    String K_PRDP_TYP = "PRDPR";
    String K_IRUL_TYP = "TIRUL";
    String K_AP_MMO = "CI";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String WS_MSGID = " ";
    String WS_MMO = " ";
    char WS_EC = ' ';
    char WS_PROC_TYP = ' ';
    short WS_I = 0;
    short WS_J = 0;
    short WS_IDX = 0;
    short WS_X = 0;
    short WS_C = 0;
    short WS_D = 0;
    int WS_LC_SEQ = 0;
    int WS_SUB_SEQ = 0;
    int WS_SEQ = 0;
    CIZQUINF_WS_HS_VAL_INF WS_HS_VAL_INF = new CIZQUINF_WS_HS_VAL_INF();
    CIZQUINF_WS_IN_VAL_INF WS_IN_VAL_INF = new CIZQUINF_WS_IN_VAL_INF();
    short WS_LTH = 0;
    int WS_LAST_LC_NO = 0;
    int WS_LAST_SEQ = 0;
    String WS_LAST_CON_TYPE = " ";
    int WS_LAST_SUB_SEQ = 0;
    char WS_MSREL_FLG = ' ';
    char WS_TYP_FLG = ' ';
    char WS_HSTOT_FLG = ' ';
    char WS_DTL_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCENPSW SCCENPSW = new SCCENPSW();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCBINF SCCBINF = new SCCBINF();
    CICOUINF CICOUINF = new CICOUINF();
    CIRTYP CIRTYP = new CIRTYP();
    CIRDTL CIRDTL = new CIRDTL();
    CIRHSTOT CIRHSTOT = new CIRHSTOT();
    CIRDINF CIRDINF = new CIRDINF();
    CIRSTYP CIRSTYP = new CIRSTYP();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    CICQUINF CICQUINF;
    public void MP(SCCGWA SCCGWA, CICQUINF CICQUINF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICQUINF = CICQUINF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIZQUINF return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, CIRTYP);
        IBS.init(SCCGWA, CIRHSTOT);
        IBS.init(SCCGWA, CICOUINF);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        B100_CHECK_INPUT();
        if (CICQUINF.FUNC == '3') {
            B240_QRY_HSTOT_INFO();
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_RECORD_NOTFND);
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        R000_CHECK_TYPE_INFO();
    }
    public void B240_QRY_HSTOT_INFO() throws IOException,SQLException,Exception {
        CIRTYP.KEY.TYPE = CICQUINF.TYPE;
        T000_READ_CITTYP();
        R000_GET_HSTOT_INFO();
    }
    public void R000_CHECK_TYPE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRTYP);
        CIRTYP.KEY.TYPE = CICQUINF.TYPE;
        T000_READ_CITTYP();
        if (WS_TYP_FLG == 'Y') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_RECORD_NOTFND);
        }
    }
    public void R000_GET_HSTOT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRHSTOT);
        CIRHSTOT.KEY.LC_OBJ_TYP = CICQUINF.OBJ_TYP.charAt(0);
        if (CICQUINF.LC_OBJ == null) CICQUINF.LC_OBJ = "";
        JIBS_tmp_int = CICQUINF.LC_OBJ.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICQUINF.LC_OBJ += " ";
        CIRHSTOT.KEY.LC_OBJ = CICQUINF.LC_OBJ.substring(0, 1);
        CIRHSTOT.CUS_AC = CICQUINF.CUS_AC;
        CIRHSTOT.KEY.LC_OBJ = CICQUINF.LC_OBJ;
        CIRHSTOT.KEY.TYPE = CICQUINF.TYPE;
        CIRHSTOT.KEY.TX_TYPE = CICQUINF.TX_TYPE;
        CIRHSTOT.KEY.RAG_FLG = CICQUINF.RAG_FLG;
        CIRHSTOT.KEY.SEQ = CICQUINF.SEQ;
        CEP.TRC(SCCGWA, CIRHSTOT.KEY.LC_OBJ_TYP);
        CEP.TRC(SCCGWA, CIRHSTOT.KEY.LC_OBJ);
        CEP.TRC(SCCGWA, CIRHSTOT.CUS_AC);
        CEP.TRC(SCCGWA, CIRHSTOT.KEY.TYPE);
        CEP.TRC(SCCGWA, CIRHSTOT.KEY.TX_TYPE);
        CEP.TRC(SCCGWA, CIRHSTOT.KEY.RAG_FLG);
        CEP.TRC(SCCGWA, CIRHSTOT.KEY.SEQ);
        T000_STARTBR_CITHSTOT();
        T000_READNEXT_CITHSTOT();
        while (WS_HSTOT_FLG != 'Y' 
            && WS_I < 9) {
            WS_I += 1;
            IBS.init(SCCGWA, CIRDTL);
            CIRDTL.KEY.LC_NO = CIRHSTOT.LC_NO;
            CIRDTL.KEY.LC_SEQ = CIRHSTOT.LC_SEQ;
            CEP.TRC(SCCGWA, CIRHSTOT.LC_NO);
            CEP.TRC(SCCGWA, CIRHSTOT.LC_SEQ);
            T000_READ_CITDTL();
            R000_GEN_OUINF_INFO();
            T000_READNEXT_CITHSTOT();
        }
        CEP.TRC(SCCGWA, WS_I);
        if (WS_HSTOT_FLG == 'Y') {
            CICOUINF.END_FLG = 'Y';
        } else {
            CICOUINF.END_FLG = 'N';
        }
        T000_ENDBR_CITHSTOT();
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "CIO50";
        SCCFMT.DATA_PTR = CICOUINF;
        SCCFMT.DATA_LEN = 5041;
        CEP.TRC(SCCGWA, CICOUINF.END_FLG);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_GEN_OUINF_INFO() throws IOException,SQLException,Exception {
        CICOUINF.LC_OBJ = CICQUINF.LC_OBJ;
        CICOUINF.LC_OBJ_TYP = CICQUINF.OBJ_TYP;
        CICOUINF.DTL[WS_I-1].LC_NO = "" + CIRHSTOT.LC_NO;
        JIBS_tmp_int = CICOUINF.DTL[WS_I-1].LC_NO.length();
        for (int i=0;i<9-JIBS_tmp_int;i++) CICOUINF.DTL[WS_I-1].LC_NO = "0" + CICOUINF.DTL[WS_I-1].LC_NO;
        CICOUINF.DTL[WS_I-1].LC_SEQ = CIRHSTOT.LC_SEQ;
        CICOUINF.DTL[WS_I-1].CUS_AC = CIRHSTOT.CUS_AC;
        if (CIRDTL.CON_TYP.trim().length() == 0) CICOUINF.DTL[WS_I-1].AC_SEQ = 0;
        else CICOUINF.DTL[WS_I-1].AC_SEQ = Integer.parseInt(CIRDTL.CON_TYP);
        CICOUINF.DTL[WS_I-1].LAST_UPD_DT = CIRHSTOT.LAST_UPD_DT;
        CICOUINF.DTL[WS_I-1].TYPE = CIRHSTOT.KEY.TYPE;
        CICOUINF.DTL[WS_I-1].TX_TYPE = CIRHSTOT.KEY.TX_TYPE;
        CICOUINF.DTL[WS_I-1].CCY = CIRHSTOT.CCY;
        CICOUINF.DTL[WS_I-1].SEQ = CIRHSTOT.KEY.SEQ;
        CICOUINF.DTL[WS_I-1].RAG_FLG = CIRHSTOT.KEY.RAG_FLG;
        WS_LTH = 469;
        if (CIRHSTOT.BLOB_LC_VAL == null) CIRHSTOT.BLOB_LC_VAL = "";
        JIBS_tmp_int = CIRHSTOT.BLOB_LC_VAL.length();
        for (int i=0;i<2000-JIBS_tmp_int;i++) CIRHSTOT.BLOB_LC_VAL += " ";
        IBS.CPY2CLS(SCCGWA, CIRHSTOT.BLOB_LC_VAL.substring(0, WS_LTH), WS_HS_VAL_INF);
        CICOUINF.DTL[WS_I-1].CUS_AC = CIRHSTOT.CUS_AC;
        CICOUINF.DTL[WS_I-1].DLY_TOT_AMT = CIRHSTOT.DLY_TOT_AMT;
        CICOUINF.DTL[WS_I-1].LAST_DLY_TOT_AMT = CIRHSTOT.LAST_DLY_TOT_AMT;
        CICOUINF.DTL[WS_I-1].DLY_TOT_VOL = CIRHSTOT.DLY_TOT_VOL;
        CICOUINF.DTL[WS_I-1].LAST_DLY_TOT_VOL = CIRHSTOT.LAST_DLY_TOT_VOL;
        CICOUINF.DTL[WS_I-1].MLY_TOT_AMT = CIRHSTOT.MLY_TOT_AMT;
        CICOUINF.DTL[WS_I-1].LAST_MLY_TOT_AMT = CIRHSTOT.LAST_MLY_TOT_AMT;
        CICOUINF.DTL[WS_I-1].MLY_TOT_VOL = CIRHSTOT.MLY_TOT_VOL;
        CICOUINF.DTL[WS_I-1].LAST_MLY_TOT_VOL = CIRHSTOT.LAST_MLY_TOT_VOL;
        CICOUINF.DTL[WS_I-1].SYY_TOT_AMT = CIRHSTOT.SYY_TOT_AMT;
        CICOUINF.DTL[WS_I-1].LAST_SYY_TOT_AMT = CIRHSTOT.LAST_SYY_TOT_AMT;
        CICOUINF.DTL[WS_I-1].YLY_TOT_AMT = CIRHSTOT.YLY_TOT_AMT;
        CICOUINF.DTL[WS_I-1].SE_DAY_LMT_AMT = CIRHSTOT.SE_DAY_LMT_AMT;
        CICOUINF.DTL[WS_I-1].SE_TM_LMT_AMT = CIRHSTOT.SE_TM_LMT_AMT;
        CICOUINF.DTL[WS_I-1].CRT_DATE = CIRHSTOT.CRT_DATE;
        CICOUINF.DTL[WS_I-1].CRT_TLR = CIRHSTOT.CRT_TLR;
        CICOUINF.DTL[WS_I-1].UPDTBL_DATE = CIRHSTOT.UPDTBL_DATE;
        CICOUINF.DTL[WS_I-1].UPDTBL_TLR = CIRHSTOT.UPDTBL_TLR;
        if (WS_DTL_FLG == 'Y') {
            CICOUINF.DTL[WS_I-1].ACT_SCENE = CIRDTL.ACT_SCENE;
        }
    }
    public void T000_READ_CITTYP() throws IOException,SQLException,Exception {
        CITTYP_RD = new DBParm();
        CITTYP_RD.TableName = "CITTYP";
        CITTYP_RD.where = "TYPE = :CIRTYP.KEY.TYPE";
        IBS.READ(SCCGWA, CIRTYP, this, CITTYP_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_RECORD_NOTFND);
        }
    }
    public void T000_STARTBR_CITHSTOT() throws IOException,SQLException,Exception {
        CITHSTOT_BR.rp = new DBParm();
        CITHSTOT_BR.rp.TableName = "CITHSTOT";
        CITHSTOT_BR.rp.where = "( TX_TYPE = :CIRHSTOT.KEY.TX_TYPE "
            + "OR :CIRHSTOT.KEY.TX_TYPE = ' ' ) "
            + "AND LC_OBJ_TYP = :CIRHSTOT.KEY.LC_OBJ_TYP "
            + "AND LC_OBJ = :CIRHSTOT.KEY.LC_OBJ "
            + "AND TYPE = :CIRHSTOT.KEY.TYPE "
            + "AND ( ( RAG_FLG > :CIRHSTOT.KEY.RAG_FLG ) "
            + "OR ( RAG_FLG = :CIRHSTOT.KEY.RAG_FLG "
            + "AND SEQ > :CIRHSTOT.KEY.SEQ ) )";
        CITHSTOT_BR.rp.order = "TS DESC,RAG_FLG,SEQ";
        IBS.STARTBR(SCCGWA, CIRHSTOT, this, CITHSTOT_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_RECORD_NOTFND);
        }
    }
    public void T000_READNEXT_CITHSTOT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRHSTOT, this, CITHSTOT_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_HSTOT_FLG = 'Y';
        } else {
            WS_HSTOT_FLG = 'N';
        }
    }
    public void T000_ENDBR_CITHSTOT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITHSTOT_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_HSTOT_FLG = 'Y';
        }
    }
    public void T000_READ_CITDTL() throws IOException,SQLException,Exception {
        CITDTL_RD = new DBParm();
        CITDTL_RD.TableName = "CITDTL";
        IBS.READ(SCCGWA, CIRDTL, CITDTL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        WS_DTL_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DTL_FLG = 'Y';
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
