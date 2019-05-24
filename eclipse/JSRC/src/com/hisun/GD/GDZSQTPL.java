package com.hisun.GD;

import com.hisun.DD.*;
import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class GDZSQTPL {
    brParm GDTHIS_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "GD850";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_X = 0;
    double WS_ADD_RAMT = 0;
    double WS_TOT_RAMT = 0;
    GDZSQTPL_WS_RAMT_ARRAY[] WS_RAMT_ARRAY = new GDZSQTPL_WS_RAMT_ARRAY[25];
    GDZSQTPL_WS_OUTPUT_INF WS_OUTPUT_INF = new GDZSQTPL_WS_OUTPUT_INF();
    int WS_TR_DATE = 0;
    long WS_JRNNO = 0;
    double WS_BAS_RAMT = 0;
    char WS_PLDR_FLG = ' ';
    char WS_HIS_FLG = ' ';
    char WS_HAVE_FLG = ' ';
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    GDCRPLDR GDCRPLDR = new GDCRPLDR();
    GDCRHIS GDCRHIS = new GDCRHIS();
    GDCOQTPL GDCOQTPL = new GDCOQTPL();
    GDRPLDR GDRPLDR = new GDRPLDR();
    GDRHIS GDRHIS = new GDRHIS();
    SCCGWA SCCGWA;
    GDCSQTPL GDCSQTPL;
    public GDZSQTPL() {
        for (int i=0;i<25;i++) WS_RAMT_ARRAY[i] = new GDZSQTPL_WS_RAMT_ARRAY();
    }
    public void MP(SCCGWA SCCGWA, GDCSQTPL GDCSQTPL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.GDCSQTPL = GDCSQTPL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "GDZSQTPL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_QUERY_PLDR_INF();
        if (pgmRtn) return;
        B030_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (GDCSQTPL.VAL.RSEQ_INF[1-1].RSEQ.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_RSEQ_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_QUERY_PLDR_INF() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 25 
            && GDCSQTPL.VAL.RSEQ_INF[WS_I-1].RSEQ.trim().length() != 0; WS_I += 1) {
            B020_01_GET_TOT_RAMT();
            if (pgmRtn) return;
            if (WS_HAVE_FLG == 'Y') {
                B020_02_GET_BAS_RAMT();
                if (pgmRtn) return;
                WS_ADD_RAMT = WS_TOT_RAMT - WS_BAS_RAMT;
            } else {
                WS_BAS_RAMT = 0;
                WS_ADD_RAMT = 0;
            }
            CEP.TRC(SCCGWA, "UUUUUU");
            CEP.TRC(SCCGWA, GDCSQTPL.VAL.RSEQ_INF[WS_I-1].RSEQ);
            WS_RAMT_ARRAY[WS_I-1].WS_O_RSEQ = GDCSQTPL.VAL.RSEQ_INF[WS_I-1].RSEQ;
            CEP.TRC(SCCGWA, WS_RAMT_ARRAY[WS_I-1].WS_O_RSEQ);
            WS_RAMT_ARRAY[WS_I-1].WS_O_BAS_RAMT = WS_BAS_RAMT;
            WS_RAMT_ARRAY[WS_I-1].WS_O_ADD_RAMT = WS_ADD_RAMT;
            WS_RAMT_ARRAY[WS_I-1].WS_O_TOT_RAMT = WS_TOT_RAMT;
            CEP.TRC(SCCGWA, WS_BAS_RAMT);
        }
    }
    public void B020_01_GET_TOT_RAMT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "TTTTT");
        CEP.TRC(SCCGWA, GDCSQTPL.VAL.RSEQ_INF[WS_I-1].RSEQ);
        WS_TOT_RAMT = 0;
        R000_STBR_PLDR_BY_RSEQ();
        if (pgmRtn) return;
        R000_READNEXT_PLDR();
        if (pgmRtn) return;
        if (WS_PLDR_FLG == 'Y') {
            WS_HAVE_FLG = 'Y';
        } else {
            WS_HAVE_FLG = 'N';
        }
        while (WS_PLDR_FLG != 'N') {
            if (GDRPLDR.RELAT_STS == 'N') {
                WS_TOT_RAMT = WS_TOT_RAMT + GDRPLDR.RELAT_AMT;
            }
            CEP.TRC(SCCGWA, WS_TOT_RAMT);
            R000_READNEXT_PLDR();
            if (pgmRtn) return;
        }
        R000_ENDBR_PLDR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, GDCSQTPL.VAL.RSEQ_INF[WS_I-1].RSEQ);
    }
    public void B020_02_GET_BAS_RAMT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSQTPL.VAL.RSEQ_INF[WS_I-1].RSEQ);
        WS_BAS_RAMT = 0;
        R000_STBR_HIS_BY_SEQFUNC_FIRST();
        if (pgmRtn) return;
        WS_TR_DATE = GDRHIS.KEY.TR_DATE;
        WS_JRNNO = GDRHIS.KEY.JRNNO;
        CEP.TRC(SCCGWA, GDCSQTPL.VAL.RSEQ_INF[WS_I-1].RSEQ);
        IBS.init(SCCGWA, GDRHIS);
        GDRHIS.KEY.TR_DATE = WS_TR_DATE;
        GDRHIS.KEY.JRNNO = WS_JRNNO;
        GDRHIS.RSEQ = GDCSQTPL.VAL.RSEQ_INF[WS_I-1].RSEQ;
        CEP.TRC(SCCGWA, GDRHIS.KEY.TR_DATE);
        CEP.TRC(SCCGWA, GDRHIS.KEY.JRNNO);
        CEP.TRC(SCCGWA, GDRHIS.RSEQ);
        GDTHIS_BR.rp = new DBParm();
        GDTHIS_BR.rp.TableName = "GDTHIS";
        GDTHIS_BR.rp.set = "WS-BAS-RAMT=IFNULL(SUM(TR_AMT),0)";
        GDTHIS_BR.rp.where = "TR_DATE = :GDRHIS.KEY.TR_DATE "
            + "AND JRNNO = :GDRHIS.KEY.JRNNO "
            + "AND RSEQ = :GDRHIS.RSEQ";
        GDTHIS_BR.rp.grp = "TR_DATE,JRNNO,RSEQ";
        GDTHIS_BR.rp.fst = true;
        IBS.STARTBR(SCCGWA, GDRHIS, this, GDTHIS_BR);
        CEP.TRC(SCCGWA, GDRHIS.TR_AMT);
        CEP.TRC(SCCGWA, WS_BAS_RAMT);
    }
    public void B030_OUTPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "RRRRR");
        IBS.init(SCCGWA, GDCOQTPL);
        for (WS_X = 1; WS_X <= 25 
            && GDCSQTPL.VAL.RSEQ_INF[WS_X-1].RSEQ.trim().length() != 0; WS_X += 1) {
            CEP.TRC(SCCGWA, WS_RAMT_ARRAY[WS_X-1].WS_O_RSEQ);
            GDCOQTPL.VAL.RSEQ_INF[WS_X-1].RSEQ = WS_RAMT_ARRAY[WS_X-1].WS_O_RSEQ;
            CEP.TRC(SCCGWA, GDCOQTPL.VAL.RSEQ_INF[WS_X-1].RSEQ);
            GDCOQTPL.VAL.RSEQ_INF[WS_X-1].RL_AMT = WS_RAMT_ARRAY[WS_X-1].WS_O_BAS_RAMT;
            GDCOQTPL.VAL.RSEQ_INF[WS_X-1].ADD_AMT = WS_RAMT_ARRAY[WS_X-1].WS_O_ADD_RAMT;
            GDCOQTPL.VAL.RSEQ_INF[WS_X-1].SUM_AMT = WS_RAMT_ARRAY[WS_X-1].WS_O_TOT_RAMT;
            CEP.TRC(SCCGWA, WS_RAMT_ARRAY[WS_X-1].WS_O_RSEQ);
            CEP.TRC(SCCGWA, WS_RAMT_ARRAY[WS_X-1].WS_O_RSEQ);
            CEP.TRC(SCCGWA, WS_RAMT_ARRAY[WS_X-1].WS_O_BAS_RAMT);
            CEP.TRC(SCCGWA, WS_RAMT_ARRAY[WS_X-1].WS_O_ADD_RAMT);
            CEP.TRC(SCCGWA, WS_RAMT_ARRAY[WS_X-1].WS_O_TOT_RAMT);
            CEP.TRC(SCCGWA, GDCOQTPL.VAL.RSEQ_INF[WS_X-1].RL_AMT);
            CEP.TRC(SCCGWA, GDCOQTPL.VAL.RSEQ_INF[WS_X-1].ADD_AMT);
            CEP.TRC(SCCGWA, GDCOQTPL.VAL.RSEQ_INF[WS_X-1].SUM_AMT);
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "GD850";
        SCCFMT.DATA_PTR = GDCOQTPL;
        SCCFMT.DATA_LEN = 1675;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_STBR_PLDR_BY_RSEQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRPLDR);
        GDRPLDR.KEY.RSEQ = GDCSQTPL.VAL.RSEQ_INF[WS_I-1].RSEQ;
        CEP.TRC(SCCGWA, "FFFFF");
        CEP.TRC(SCCGWA, GDCSQTPL.VAL.RSEQ_INF[WS_I-1].RSEQ);
        CEP.TRC(SCCGWA, GDRPLDR.KEY.RSEQ);
        GDCRPLDR.FUNC = 'B';
        GDCRPLDR.OPT = 'Q';
        GDCRPLDR.REC_PTR = GDRPLDR;
        GDCRPLDR.REC_LEN = 311;
        S000_CALL_GDZRPLDR();
        if (pgmRtn) return;
    }
    public void R000_READNEXT_PLDR() throws IOException,SQLException,Exception {
        GDCRPLDR.FUNC = 'B';
        GDCRPLDR.OPT = 'R';
        GDCRPLDR.REC_PTR = GDRPLDR;
        GDCRPLDR.REC_LEN = 311;
        S000_CALL_GDZRPLDR();
        if (pgmRtn) return;
        if (GDCRPLDR.RC.RC_CODE != 0) {
            WS_PLDR_FLG = 'N';
        } else {
            WS_PLDR_FLG = 'Y';
        }
    }
    public void R000_ENDBR_PLDR() throws IOException,SQLException,Exception {
        GDCRPLDR.FUNC = 'B';
        GDCRPLDR.OPT = 'E';
        GDCRPLDR.REC_PTR = GDRPLDR;
        GDCRPLDR.REC_LEN = 311;
        S000_CALL_GDZRPLDR();
        if (pgmRtn) return;
    }
    public void R000_STBR_HIS_BY_SEQFUNC_FIRST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRHIS);
        IBS.init(SCCGWA, GDCRHIS);
        GDRHIS.RSEQ = GDCSQTPL.VAL.RSEQ_INF[WS_I-1].RSEQ;
        CEP.TRC(SCCGWA, GDCSQTPL.VAL.RSEQ_INF[WS_I-1].RSEQ);
        CEP.TRC(SCCGWA, GDRHIS.RSEQ);
        GDRHIS.FUNC = '3';
        GDRHIS.CAN_FLG = 'N';
        GDCRHIS.FUNC = 'B';
        GDCRHIS.OPT = 'F';
        GDCRHIS.REC_PTR = GDRHIS;
        GDCRHIS.REC_LEN = 281;
        S000_CALL_GDZRHIS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, GDRHIS.KEY.TR_DATE);
        CEP.TRC(SCCGWA, GDRHIS.KEY.JRNNO);
        if (GDCRHIS.RC.RC_CODE != 0) {
            WS_HIS_FLG = 'N';
        } else {
            WS_HIS_FLG = 'Y';
        }
    }
    public void R000_READNEXT_HIS() throws IOException,SQLException,Exception {
        GDCRHIS.FUNC = 'B';
        GDCRHIS.OPT = 'R';
        GDCRHIS.REC_PTR = GDRHIS;
        GDCRHIS.REC_LEN = 281;
        S000_CALL_GDZRHIS();
        if (pgmRtn) return;
        if (GDCRHIS.RC.RC_CODE != 0) {
            WS_HIS_FLG = 'N';
        } else {
            WS_HIS_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, WS_HIS_FLG);
        CEP.TRC(SCCGWA, GDRHIS.RSEQ);
    }
    public void R000_ENDBR_HIS() throws IOException,SQLException,Exception {
        GDCRHIS.FUNC = 'B';
        GDCRHIS.OPT = 'E';
        GDCRHIS.REC_PTR = GDRHIS;
        GDCRHIS.REC_LEN = 281;
        S000_CALL_GDZRHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_GDZRHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SRC-GDZRHIS", GDCRHIS);
    }
    public void S000_CALL_GDZRPLDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SRC-GDZRPLDR", GDCRPLDR);
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
