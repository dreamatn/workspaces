package com.hisun.DD;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.DB.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSADIF {
    DBParm DDTADIF_RD;
    brParm DDTADIF_BR = new brParm();
    DBParm DDTADMN_RD;
    DBParm DDTCCY_RD;
    DBParm DDTMST_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DD631";
    String K_OUTPUT_FMT_INQ = "DD630";
    int K_SCR_ROW_NO = 10;
    String WS_ERR_MSG = " ";
    String WS_ADP_NO = " ";
    String WS_AC_CNM = " ";
    String WS_ADP_TYPE = " ";
    String WS_MSGID = " ";
    short WS_FLD_NO = 0;
    short WS_CNT = 0;
    short WS_TOTAL_NUM = 0;
    short WS_TOTAL_PAGE = 0;
    short WS_REMAINDER = 0;
    char WS_ADIF_FLG = ' ';
    char WS_CHG_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DDCOADMN DDCOADMN = new DDCOADMN();
    CICACCU CICACCU = new CICACCU();
    CICQACAC CICQACAC = new CICQACAC();
    DDCIMCYY DDCIMCYY = new DDCIMCYY();
    DDCOADIF DDCOADIF = new DDCOADIF();
    DDCF6300 DDCF6300 = new DDCF6300();
    DDRCCY DDRCCY = new DDRCCY();
    DDRMST DDRMST = new DDRMST();
    DDRADMN DDRADMN = new DDRADMN();
    DDRADIF DDRADIF = new DDRADIF();
    int WS_COUNT = 0;
    int WS_RCD_SEQ = 0;
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    DDCSADIF DDCSADIF;
    public void MP(SCCGWA SCCGWA, DDCSADIF DDCSADIF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSADIF = DDCSADIF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSADIF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (DDCSADIF.FUNC == '1') {
            B200_INQ_ADIF_REC_PROC();
            if (pgmRtn) return;
        } else if (DDCSADIF.FUNC == '2') {
            B300_MOD_ADIF_REC_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID TX TYPE(" + DDCSADIF.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSADIF.AC;
        T000_READ_DDTMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, DDRMST.CI_TYP);
        CEP.TRC(SCCGWA, DDRMST.AC_STS);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_MST_REC_NOTFND);
        }
        if (DDRMST.AC_STS != 'N') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ACTION_INVALID);
        }
    }
    public void B150_GET_ACAC_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = DDCSADIF.AC;
        CICQACAC.DATA.CCY_ACAC = DDCSADIF.CCY;
        CICQACAC.DATA.CR_FLG = DDCSADIF.CCY_TYP;
        CEP.TRC(SCCGWA, CICQACAC.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CICQACAC.DATA.CCY_ACAC);
        CEP.TRC(SCCGWA, CICQACAC.DATA.CR_FLG);
        CICQACAC.FUNC = 'C';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ACAC_NO_NOT_EXIT);
        }
    }
    public void B200_INQ_ADIF_REC_PROC() throws IOException,SQLException,Exception {
        B150_GET_ACAC_NO();
        if (pgmRtn) return;
        if (DDCSADIF.PAGE_ROW == 0) {
            DDCSADIF.PAGE_ROW = (short) K_SCR_ROW_NO;
        }
        DDRADIF.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_GROUP_DDTADIF();
        if (pgmRtn) return;
        WS_TOTAL_NUM = (short) WS_COUNT;
        WS_REMAINDER = (short) (WS_TOTAL_NUM % DDCSADIF.PAGE_ROW);
        WS_TOTAL_PAGE = (short) ((WS_TOTAL_NUM - WS_REMAINDER) / DDCSADIF.PAGE_ROW);
        if (WS_REMAINDER != 0) {
            WS_TOTAL_PAGE += 1;
        }
        IBS.init(SCCGWA, DDRADIF);
        DDRADIF.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_STARTBR_DDTADIF();
        if (pgmRtn) return;
        if (DDCSADIF.PAGE_NUM == 0) {
            WS_RCD_SEQ = 1;
        } else {
            WS_RCD_SEQ = ( DDCSADIF.PAGE_NUM - 1 ) * DDCSADIF.PAGE_ROW + 1;
        }
        CEP.TRC(SCCGWA, DDCSADIF.PAGE_ROW);
        CEP.TRC(SCCGWA, DDCSADIF.PAGE_NUM);
        CEP.TRC(SCCGWA, WS_TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_RCD_SEQ);
        T000_READNEXT_DDTADIF_FIRST();
        if (pgmRtn) return;
        for (WS_CNT = 1; WS_CNT <= DDCSADIF.PAGE_ROW 
            && WS_ADIF_FLG != 'N'; WS_CNT += 1) {
            CEP.TRC(SCCGWA, DDRADIF.KEY.ADP_TYPE);
            CEP.TRC(SCCGWA, DDRADIF.KEY.ACO_AC);
            CEP.TRC(SCCGWA, WS_CNT);
            B210_OUTPUT_DATA_INQ();
            if (pgmRtn) return;
            T000_READNEXT_DDTADIF();
            if (pgmRtn) return;
            WS_RCD_SEQ += 1;
        }
        CEP.TRC(SCCGWA, WS_RCD_SEQ);
        DDCF6300.TOT_PAGE = WS_TOTAL_PAGE;
        DDCF6300.TOT_NUM = WS_TOTAL_NUM;
        if (DDCSADIF.PAGE_NUM == 0) {
            DDCF6300.CURR_PAGE = 1;
        } else {
            DDCF6300.CURR_PAGE = DDCSADIF.PAGE_NUM;
        }
        CEP.TRC(SCCGWA, WS_CNT);
        if (WS_ADIF_FLG == 'N') {
            DDCF6300.LAST_IND = 'Y';
            DDCF6300.CURR_ROW = (short) (WS_CNT - 1);
        } else {
            DDCF6300.CURR_ROW = DDCSADIF.PAGE_ROW;
        }
        CEP.TRC(SCCGWA, DDCF6300.TOT_PAGE);
        CEP.TRC(SCCGWA, DDCF6300.TOT_NUM);
        CEP.TRC(SCCGWA, DDCF6300.CURR_PAGE);
        CEP.TRC(SCCGWA, DDCF6300.LAST_IND);
        CEP.TRC(SCCGWA, DDCF6300.CURR_ROW);
        T000_ENDBR_DDTADIF();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_INQ;
        SCCFMT.DATA_PTR = DDCF6300;
        SCCFMT.DATA_LEN = 887;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B300_MOD_ADIF_REC_PROC() throws IOException,SQLException,Exception {
        B150_GET_ACAC_NO();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRADIF);
        DDRADIF.KEY.OD_STRDATE = DDCSADIF.STR_DATE;
        DDRADIF.KEY.ADP_TYPE = DDCSADIF.ADP_TYPE;
        DDRADIF.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READUPDATE_DDTADIF();
        if (pgmRtn) return;
        if (WS_ADIF_FLG == 'N') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ADIF_REC_NOTFND);
        }
        CEP.TRC(SCCGWA, DDRADIF.OD_STS);
        if (DDRADIF.OD_STS != '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_OD_STS_NOTNORMAL);
        }
        CEP.TRC(SCCGWA, DDRADIF.OD_EXPDATE);
        CEP.TRC(SCCGWA, DDCSADIF.EXP_DATE);
        if (DDCSADIF.EXP_DATE != DDRADIF.OD_EXPDATE) {
            WS_CHG_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, WS_CHG_FLG);
        if (WS_CHG_FLG == 'Y') {
            DDRADIF.OD_EXPDATE = DDCSADIF.EXP_DATE;
            DDRADIF.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRADIF.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTADIF();
            if (pgmRtn) return;
        }
        B310_OUTPUT_DATA_MOD();
        if (pgmRtn) return;
    }
    public void B210_OUTPUT_DATA_INQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCF6300);
        DDCF6300.OUTPUT_DATA[WS_CNT-1].AC = DDCSADIF.AC;
        DDCF6300.OUTPUT_DATA[WS_CNT-1].CCY = DDCSADIF.CCY;
        DDCF6300.OUTPUT_DATA[WS_CNT-1].CCY_TYP = DDCSADIF.CCY_TYP;
        DDCF6300.OUTPUT_DATA[WS_CNT-1].ADP_TYPE = DDRADIF.KEY.ADP_TYPE;
        DDCF6300.OUTPUT_DATA[WS_CNT-1].STR_DATE = DDRADIF.KEY.OD_STRDATE;
        DDCF6300.OUTPUT_DATA[WS_CNT-1].EXP_DATE = DDRADIF.OD_EXPDATE;
        DDCF6300.OUTPUT_DATA[WS_CNT-1].OD_STS = DDRADIF.OD_STS;
        DDCF6300.OUTPUT_DATA[WS_CNT-1].OD_AMT = DDRADIF.OD_AMT;
        DDCF6300.OUTPUT_DATA[WS_CNT-1].OD_BAL = DDRADIF.OD_BAL;
        CEP.TRC(SCCGWA, DDCF6300.OUTPUT_DATA[WS_CNT-1].ADP_TYPE);
        CEP.TRC(SCCGWA, DDCF6300.OUTPUT_DATA[WS_CNT-1].AC);
        CEP.TRC(SCCGWA, DDCF6300.OUTPUT_DATA[WS_CNT-1].CCY);
        CEP.TRC(SCCGWA, DDCF6300.OUTPUT_DATA[WS_CNT-1].CCY_TYP);
        CEP.TRC(SCCGWA, DDCF6300.OUTPUT_DATA[WS_CNT-1].STR_DATE);
        CEP.TRC(SCCGWA, DDCF6300.OUTPUT_DATA[WS_CNT-1].EXP_DATE);
        CEP.TRC(SCCGWA, DDCF6300.OUTPUT_DATA[WS_CNT-1].OD_STS);
        CEP.TRC(SCCGWA, DDCF6300.OUTPUT_DATA[WS_CNT-1].OD_AMT);
        CEP.TRC(SCCGWA, DDCF6300.OUTPUT_DATA[WS_CNT-1].OD_BAL);
    }
    public void B310_OUTPUT_DATA_MOD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOADIF);
        DDCOADIF.AC = DDCSADIF.AC;
        DDCOADIF.CCY = DDCSADIF.CCY;
        DDCOADIF.CCY_TYP = DDCSADIF.CCY_TYP;
        DDCOADIF.ADP_TYPE = DDRADIF.KEY.ADP_TYPE;
        DDCOADIF.STR_DATE = DDRADIF.KEY.OD_STRDATE;
        DDCOADIF.EXP_DATE = DDRADIF.OD_EXPDATE;
        DDCOADIF.OD_STS = DDRADIF.OD_STS;
        DDCOADIF.OD_AMT = DDRADIF.OD_AMT;
        DDCOADIF.OD_BAL = DDRADIF.OD_BAL;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCOADIF;
        SCCFMT.DATA_LEN = 87;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_MPAGE_OUTPUT_START() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 87;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_READUPDATE_DDTADIF() throws IOException,SQLException,Exception {
        DDTADIF_RD = new DBParm();
        DDTADIF_RD.TableName = "DDTADIF";
        DDTADIF_RD.upd = true;
        IBS.READ(SCCGWA, DDRADIF, DDTADIF_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ADIF_FLG = 'F';
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_ADIF_FLG = 'N';
            }
        }
    }
    public void T000_REWRITE_DDTADIF() throws IOException,SQLException,Exception {
        DDTADIF_RD = new DBParm();
        DDTADIF_RD.TableName = "DDTADIF";
        IBS.REWRITE(SCCGWA, DDRADIF, DDTADIF_RD);
    }
    public void T000_GROUP_DDTADIF() throws IOException,SQLException,Exception {
        DDTADIF_RD = new DBParm();
        DDTADIF_RD.TableName = "DDTADIF";
        DDTADIF_RD.set = "WS-COUNT=COUNT(*)";
        DDTADIF_RD.where = "ACO_AC = :DDRADIF.KEY.ACO_AC";
        IBS.GROUP(SCCGWA, DDRADIF, this, DDTADIF_RD);
        CEP.TRC(SCCGWA, WS_COUNT);
    }
    public void T000_STARTBR_DDTADIF() throws IOException,SQLException,Exception {
        DDTADIF_BR.rp = new DBParm();
        DDTADIF_BR.rp.TableName = "DDTADIF";
        DDTADIF_BR.rp.where = "ACO_AC = :DDRADIF.KEY.ACO_AC";
        IBS.STARTBR(SCCGWA, DDRADIF, this, DDTADIF_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ADIF_FLG = 'F';
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_ADIF_FLG = 'N';
            }
        }
    }
    public void T000_READNEXT_DDTADIF_FIRST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRADIF, this, DDTADIF_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ADIF_FLG = 'F';
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_ADIF_FLG = 'N';
            }
        }
    }
    public void T000_READNEXT_DDTADIF() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRADIF, this, DDTADIF_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ADIF_FLG = 'F';
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_ADIF_FLG = 'N';
            }
        }
    }
    public void T000_ENDBR_DDTADIF() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTADIF_BR);
    }
    public void R000_READ_DDTADMN() throws IOException,SQLException,Exception {
        DDTADMN_RD = new DBParm();
        DDTADMN_RD.TableName = "DDTADMN";
        IBS.READ(SCCGWA, DDRADMN, DDTADMN_RD);
    }
    public void R000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRMST.KEY.CUS_AC);
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
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
