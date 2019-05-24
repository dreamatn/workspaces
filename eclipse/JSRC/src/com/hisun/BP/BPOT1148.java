package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1148 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BP061";
    String CPN_FFAV_MAINTAIN = "BP-F-S-MAINTAIN-FFAV";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_CNT1 = 0;
    String WS_TXT = " ";
    double WS_AMT = 0;
    short WS_CNT = 0;
    short WS_INPT_CNT = 0;
    short WS_J = 0;
    String WS_FAV_TYP = " ";
    char WS_COLL_FLG = ' ';
    char WS_FAV_KND = ' ';
    char WS_CAL_MTH = ' ';
    char WS_CAL_CYC = ' ';
    char WS_COL_TYPE = ' ';
    char WS_ARG_SPL = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOFFAV BPCOFFAV = new BPCOFFAV();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    BPB1148_AWA_1148 BPB1148_AWA_1148;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1148 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1148_AWA_1148>");
        BPB1148_AWA_1148 = (BPB1148_AWA_1148) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_DELETE_FAVINF_PARM();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB1148_AWA_1148.FAV_CD);
        if (BPB1148_AWA_1148.FAV_CD.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PRFR_CD_MUSTINPUT;
            WS_FLD_NO = BPB1148_AWA_1148.FAV_CD_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_DELETE_FAVINF_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFFAV);
        BPCOFFAV.FUNC = 'D';
        BPCOFFAV.OUTPUT_FMT = K_OUTPUT_FMT;
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZFSFAV();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, BPB1148_AWA_1148.FAV_CD, BPCOFFAV.KEY.PRFR_CODE);
        BPCOFFAV.VAL.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCOFFAV.VAL.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
    }
    public void S000_CALL_BPZFSFAV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_FFAV_MAINTAIN, BPCOFFAV);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_SCSSCKDT() throws IOException,SQLException,Exception {
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
