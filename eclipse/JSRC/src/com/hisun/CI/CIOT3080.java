package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT3080 {
    DBParm CITCMPR_RD;
    brParm CITDTL_BR = new brParm();
    brParm CITDINF_BR = new brParm();
    String K_PRDP_TYP = "PRDPR";
    String K_IRUL_TYP = "TIRUL";
    String K_AP_MMO = "TD";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String PGM_SCSSCKDT = "SCSSCKDT";
    short WS_I = 0;
    short WS_N = 0;
    int WS_AC_DATE = 0;
    int WS_LS_LC_SEQ = 0;
    int WS_LAST_SEQ = 0;
    short WS_LAST_CON_SEQ = 0;
    String WS_LAST_CON_TYP = " ";
    int WS_LAST_SUB_SEQ = 0;
    short YY = 0;
    short MM = 0;
    short DD = 0;
    short R = 0;
    char WS_DTL_FLG = ' ';
    char WS_DINF_FLG = ' ';
    char WS_CMPR_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCENPSW SCCENPSW = new SCCENPSW();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCBINF SCCBINF = new SCCBINF();
    CICO3080 CICO3080 = new CICO3080();
    CIRTYP CIRTYP = new CIRTYP();
    CIRCMPR CIRCMPR = new CIRCMPR();
    CIRDTL CIRDTL = new CIRDTL();
    CIRDINF CIRDINF = new CIRDINF();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    CIB3080_AWA_3080 CIB3080_AWA_3080;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT3080 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB3080_AWA_3080>");
        CIB3080_AWA_3080 = (CIB3080_AWA_3080) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB3080_AWA_3080.TYPE);
        CEP.TRC(SCCGWA, CIB3080_AWA_3080.TX_TYPE);
        B100_CHECK_INPUT();
        B200_BROWSER_INFO();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (CIB3080_AWA_3080.TYPE.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_KIROKU_NOTFND);
        }
        if (CIB3080_AWA_3080.TX_TYPE.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_TX_M_INP);
        }
    }
    public void B200_BROWSER_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRCMPR);
        CIRCMPR.KEY.TYPE = CIB3080_AWA_3080.TYPE;
        CIRCMPR.KEY.TX_TYPE = CIB3080_AWA_3080.TX_TYPE;
        T000_READ_CITCMPR();
        if (WS_CMPR_FLG == 'Y') {
            IBS.init(SCCGWA, CIRDTL);
            WS_LS_LC_SEQ = 0;
            CIRDTL.KEY.LC_NO = CIRCMPR.LC_NO;
            WS_LS_LC_SEQ = CIB3080_AWA_3080.LST_SEQ1;
            CIRDTL.TY_FLG = 'Y';
            WS_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
            T000_STARTBR_CITDTL();
            T000_READNEXT_CITDTL();
            while (WS_I < 4 
                && WS_DTL_FLG != 'Y') {
                WS_I += 1;
                R000_GEN_DTL_INFO();
                R000_BROWSER_DINF_INFO();
                T000_READNEXT_CITDTL();
            }
            T000_ENDBR_CITDTL();
            if (WS_DTL_FLG == 'Y') {
                CICO3080.END_FLG = 'Y';
            } else {
                CICO3080.END_FLG = 'N';
            }
            IBS.init(SCCGWA, SCCFMT);
            SCCFMT.FMTID = "CI080";
            SCCFMT.DATA_PTR = CICO3080;
            SCCFMT.DATA_LEN = 6165;
            IBS.FMT(SCCGWA, SCCFMT);
        }
    }
    public void R000_GEN_DTL_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIRDTL.KEY.LC_NO);
        CEP.TRC(SCCGWA, CIRDTL.KEY.LC_SEQ);
        CICO3080.DTL[WS_I-1].LC_NO = CIRDTL.KEY.LC_NO;
        CICO3080.DTL[WS_I-1].SEQ = CIRDTL.KEY.LC_SEQ;
        CICO3080.DTL[WS_I-1].CON_TYP = CIRDTL.CON_TYP;
        CICO3080.DTL[WS_I-1].LVL_CON_TYP = CIRDTL.LVL_CON_TYP;
        CICO3080.DTL[WS_I-1].LVL = CIRDTL.LVL;
        CICO3080.DTL[WS_I-1].LC_CCY = CIRDTL.LC_CCY;
        CICO3080.DTL[WS_I-1].DAY_STA_DT = CIRDTL.DAY_STA_DT;
        CICO3080.DTL[WS_I-1].DAY_END_DT = CIRDTL.DAY_END_DT;
        CICO3080.DTL[WS_I-1].TXN_LMT_AMT = CIRDTL.TXN_LMT_AMT;
        CICO3080.DTL[WS_I-1].DLY_LMT_AMT = CIRDTL.DLY_LMT_AMT;
        CICO3080.DTL[WS_I-1].DLY_LMT_VOL = CIRDTL.DLY_LMT_VOL;
        CICO3080.DTL[WS_I-1].MLY_LMT_AMT = CIRDTL.MLY_LMT_AMT;
        CICO3080.DTL[WS_I-1].MLY_LMT_VOL = CIRDTL.MLY_LMT_VOL;
        CICO3080.DTL[WS_I-1].SYY_LMT_AMT = CIRDTL.SYY_LMT_AMT;
        CICO3080.DTL[WS_I-1].YLY_LMT_AMT = CIRDTL.YLY_LMT_AMT;
        CICO3080.DTL[WS_I-1].TM_LMT_AMT = CIRDTL.TM_LMT_AMT;
        CICO3080.DTL[WS_I-1].SE_LMT_AMT = CIRDTL.SE_LMT_AMT;
        CICO3080.DTL[WS_I-1].LMT_AMT_1 = CIRDTL.LMT_AMT_1;
        CICO3080.DTL[WS_I-1].LMT_AMT_2 = CIRDTL.LMT_AMT_2;
        CICO3080.DTL[WS_I-1].LMT_AMT_3 = CIRDTL.LMT_AMT_3;
        CICO3080.DTL[WS_I-1].LMT_AMT_4 = CIRDTL.LMT_AMT_4;
        CICO3080.DTL[WS_I-1].REL_TYP = CIRDTL.REL_TYP;
        CICO3080.DTL[WS_I-1].BAL_LMT_AMT = CIRDTL.BAL_LMT_AMT;
        CICO3080.DTL[WS_I-1].BAL_TYP = CIRDTL.BAL_TYP;
        CICO3080.DTL[WS_I-1].LMT_CTL_STSW = CIRDTL.LMT_CTL_STSW;
        CICO3080.DTL[WS_I-1].ACT_SCENE = CIRDTL.ACT_SCENE;
    }
    public void R000_BROWSER_DINF_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRDINF);
        WS_LAST_SEQ = 0;
        WS_LAST_CON_SEQ = 0;
        WS_LAST_CON_TYP = " ";
        WS_LAST_SUB_SEQ = 0;
        CIRDINF.KEY.LC_NO = CIRDTL.KEY.LC_NO;
        CIRDINF.KEY.LC_SEQ = CIRDTL.KEY.LC_SEQ;
        WS_LAST_SEQ = CIB3080_AWA_3080.LST_SEQ2;
        WS_LAST_CON_SEQ = CIB3080_AWA_3080.LST_CSEQ;
        WS_LAST_CON_TYP = CIB3080_AWA_3080.LST_CTYP;
        WS_LAST_SUB_SEQ = CIB3080_AWA_3080.LST_SUBS;
        T000_STARTBR_CITDINF();
        T000_READNEXT_CITDINF();
        while (WS_N < 50 
            && WS_DINF_FLG != 'Y') {
            WS_N += 1;
            CEP.TRC(SCCGWA, CIRDINF.KEY.LC_NO);
            CEP.TRC(SCCGWA, CIRDINF.KEY.LC_SEQ);
            R000_GEN_DINF_INFO();
            T000_READNEXT_CITDINF();
        }
    }
    public void R000_GEN_DINF_INFO() throws IOException,SQLException,Exception {
        CICO3080.VAL_INFO[WS_N-1].LC_NO2 = CIRDINF.KEY.LC_NO;
        CICO3080.VAL_INFO[WS_N-1].SEQ2 = CIRDINF.KEY.LC_SEQ;
        CICO3080.VAL_INFO[WS_N-1].CON_SEQ = CIRDINF.KEY.CON_SEQ;
        CICO3080.VAL_INFO[WS_N-1].SUB_SEQ2 = CIRDINF.SUB_SEQ;
        if (CIRDINF.KEY.CON_TYP.trim().length() == 0) CICO3080.VAL_INFO[WS_N-1].CON_TYP2 = 0;
        else CICO3080.VAL_INFO[WS_N-1].CON_TYP2 = Short.parseShort(CIRDINF.KEY.CON_TYP);
        CICO3080.VAL_INFO[WS_N-1].VAL = CIRDINF.VAL;
        CICO3080.VAL_INFO[WS_N-1].VAL_COND = CIRDINF.VAL_COND;
    }
    public void T000_READ_CITCMPR() throws IOException,SQLException,Exception {
        CITCMPR_RD = new DBParm();
        CITCMPR_RD.TableName = "CITCMPR";
        IBS.READ(SCCGWA, CIRCMPR, CITCMPR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CMPR_FLG = 'Y';
        } else {
            WS_CMPR_FLG = 'N';
        }
    }
    public void T000_STARTBR_CITDTL() throws IOException,SQLException,Exception {
        CITDTL_BR.rp = new DBParm();
        CITDTL_BR.rp.TableName = "CITDTL";
        CITDTL_BR.rp.where = "LC_NO = :CIRDTL.KEY.LC_NO "
            + "AND LC_SEQ > :WS_LS_LC_SEQ "
            + "AND TY_FLG < > :CIRDTL.TY_FLG "
            + "AND DAY_STA_DT <= :WS_AC_DATE "
            + "AND DAY_END_DT >= :WS_AC_DATE";
        CITDTL_BR.rp.order = "LC_SEQ";
        IBS.STARTBR(SCCGWA, CIRDTL, this, CITDTL_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READNEXT_CITDTL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRDTL, this, CITDTL_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DTL_FLG = 'Y';
        } else {
            WS_DTL_FLG = 'N';
        }
    }
    public void T000_ENDBR_CITDTL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITDTL_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_STARTBR_CITDINF() throws IOException,SQLException,Exception {
        CITDINF_BR.rp = new DBParm();
        CITDINF_BR.rp.TableName = "CITDINF";
        CITDINF_BR.rp.where = "LC_NO = :CIRDINF.KEY.LC_NO "
            + "AND LC_SEQ = :CIRDINF.KEY.LC_SEQ "
            + "AND ( ( LC_NO = :CIRDINF.KEY.LC_NO "
            + "AND LC_SEQ > :WS_LAST_SEQ ) "
            + "OR ( LC_NO = :CIRDINF.KEY.LC_NO "
            + "AND LC_SEQ = :WS_LAST_SEQ "
            + "AND CON_SEQ > :WS_LAST_CON_SEQ ) "
            + "OR ( LC_NO = :CIRDINF.KEY.LC_NO "
            + "AND LC_SEQ = :WS_LAST_SEQ "
            + "AND CON_SEQ = :WS_LAST_CON_SEQ "
            + "AND CON_TYP > :WS_LAST_CON_TYP ) "
            + "OR ( LC_NO = :CIRDINF.KEY.LC_NO "
            + "AND LC_SEQ = :WS_LAST_SEQ "
            + "AND CON_SEQ = :WS_LAST_CON_SEQ "
            + "AND CON_TYP = :WS_LAST_CON_TYP "
            + "AND SUB_SEQ > :WS_LAST_SUB_SEQ ) )";
        CITDINF_BR.rp.order = "LC_NO,LC_SEQ,CON_SEQ,CON_TYP,SUB_SEQ";
        IBS.STARTBR(SCCGWA, CIRDINF, this, CITDINF_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READNEXT_CITDINF() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRDINF, this, CITDINF_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DINF_FLG = 'N';
        } else {
            WS_DINF_FLG = 'Y';
        }
    }
    public void T000_ENDBR_CITDINF() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITDINF_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
