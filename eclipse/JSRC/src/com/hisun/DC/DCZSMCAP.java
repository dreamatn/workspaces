package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.util.StringTokenizer;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSMCAP {
    StringTokenizer JIBS_stb;
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm DCTCITAP_RD;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT_A = "DC927";
    String K_HIS_RMK = "APPLY FOR CITIZEN CARDS";
    String CPN_BPZSGSEQ = "BP-S-GET-SEQ";
    String CPN_CARD_INF = "DC-U-CARD-INF ";
    String CPN_U_BPZPNHIS = "BP-REC-NHIS";
    String WS_APP_BAT_NO = " ";
    String WS_MKD_FILE_NM = " ";
    char DCZSMCAP_FILLER3 = 0X02;
    int WS_APP_DT = 0;
    String WS_MSG_ID = "      ";
    DCZSMCAP_WS_TEMP_BATNO WS_TEMP_BATNO = new DCZSMCAP_WS_TEMP_BATNO();
    String WS_BATCHNO = " ";
    int WS_APPI_NUM = 0;
    String WS_FILNAM_AREA1 = " ";
    String WS_FILNAM_AREA2 = " ";
    String WS_FILNAM_AREA3 = " ";
    int WS_FILNAM_AREA4 = 0;
    String WS_FILNAM_AREA5 = " ";
    String WS_TS = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    DCRCITAP DCRCITAP = new DCRCITAP();
    DCCF927 DCCF927 = new DCCF927();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCSMCAP DCCSMCAP;
    public void MP(SCCGWA SCCGWA, DCCSMCAP DCCSMCAP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSMCAP = DCCSMCAP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSMCAP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "1111");
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "2222");
        B100_INPUT_CHK_PROC();
        if (pgmRtn) return;
        B200_APPLY_RECORD();
        if (pgmRtn) return;
        C000_HISTORY_RECORD();
        if (pgmRtn) return;
        C100_OUTPUT_LIST();
        if (pgmRtn) return;
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        if (DCCSMCAP.APP_FILE_NM.trim().length() == 0) {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSMCAP.APP_FILE_NM.trim().length() > 0) {
            JIBS_stb = new StringTokenizer(DCCSMCAP.APP_FILE_NM, "_");
            if (JIBS_stb.hasMoreTokens()) WS_FILNAM_AREA1 = JIBS_stb.nextToken();
            if (JIBS_stb.hasMoreTokens()) WS_FILNAM_AREA2 = JIBS_stb.nextToken();
            if (JIBS_stb.hasMoreTokens()) WS_FILNAM_AREA3 = JIBS_stb.nextToken();
            if (JIBS_stb.hasMoreTokens()) WS_FILNAM_AREA4 = JIBS_stb.nextToken();
            if (JIBS_stb.hasMoreTokens()) WS_FILNAM_AREA5 = JIBS_stb.nextToken();
            WS_APPI_NUM = WS_FILNAM_AREA4;
            if (WS_APPI_NUM >= 1000000) {
                WS_MSG_ID = DCCMSG_ERROR_MSG.DC_NUM_EXC_LMT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B200_APPLY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = "DC";
        BPCSGSEQ.CODE = "BATNO";
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        BPCSGSEQ.NUM = 1;
        S000_CALL_BPZSGSEQ();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = "" + BPCSGSEQ.SEQ;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(12 - 1, 12 + 4 - 1).trim().length() == 0) WS_TEMP_BATNO.WS_TEMP_SEQ = 0;
        else WS_TEMP_BATNO.WS_TEMP_SEQ = Short.parseShort(JIBS_tmp_str[0].substring(12 - 1, 12 + 4 - 1));
        WS_TEMP_BATNO.WS_TEMP_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_BATCHNO = IBS.CLS2CPY(SCCGWA, WS_TEMP_BATNO);
        IBS.init(SCCGWA, DCRCITAP);
        DCRCITAP.KEY.APP_BAT_NO = WS_BATCHNO;
        DCRCITAP.APP_NUM = WS_APPI_NUM;
        DCRCITAP.RECV_FILE_NM = DCCSMCAP.APP_FILE_NM;
        DCRCITAP.RCD_STS = '0';
        DCRCITAP.CRT_NUM = 0;
        DCRCITAP.TXN_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRCITAP.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCITAP.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRCITAP.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCITAP.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DCTCITAP();
        if (pgmRtn) return;
    }
    public void C000_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMK;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "DCRCITAP";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TOOL = DCRCITAP.KEY.APP_BAT_NO;
        BPCPNHIS.INFO.FMT_ID_LEN = 235;
        BPCPNHIS.INFO.NEW_DAT_PT = DCRCITAP;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void C100_OUTPUT_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCF927);
        DCCF927.APP_BAT_NO = DCRCITAP.KEY.APP_BAT_NO;
        DCCF927.MKD_FILE_NM = DCRCITAP.RECV_FILE_NM;
        DCCF927.APP_DT = DCRCITAP.TXN_DT;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_A;
        SCCFMT.DATA_PTR = DCCF927;
        SCCFMT.DATA_LEN = 104;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZPNHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BPZSGSEQ, BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ID);
    }
    public void T000_WRITE_DCTCITAP() throws IOException,SQLException,Exception {
        DCTCITAP_RD = new DBParm();
        DCTCITAP_RD.TableName = "DCTCITAP";
        IBS.WRITE(SCCGWA, DCRCITAP, DCTCITAP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '4') {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_REC_ALR_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCITAP";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
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
