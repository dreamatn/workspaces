package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.text.DecimalFormat;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT6610 {
    DecimalFormat df;
    brParm BPTRHIS_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    BPOT6610_WS_OUTPUT WS_OUTPUT = new BPOT6610_WS_OUTPUT();
    char WS_RHIS_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRRHIS BPRRHIS = new BPRRHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB6610_AWA_6610 BPB6610_AWA_6610;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT6610 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB6610_AWA_6610>");
        BPB6610_AWA_6610 = (BPB6610_AWA_6610) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_BRW_BPTRHIS();
        if (pgmRtn) return;
        R000_INIT_QUEUE();
        if (pgmRtn) return;
        T000_FETCH_BPTRHIS();
        if (pgmRtn) return;
        while (WS_RHIS_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            WS_OUTPUT.WS_DATE = BPRRHIS.KEY.DATE;
            df = new DecimalFormat("########0");
            WS_OUTPUT.WS_JRNNO = df.format(BPRRHIS.KEY.JRNNO);
            if (BPRRHIS.KEY.TYPE == 'S') {
                WS_OUTPUT.WS_FUNC = "REQUEST";
            } else if (BPRRHIS.KEY.TYPE == 'R') {
                WS_OUTPUT.WS_FUNC = "REPLY";
            } else {
                WS_OUTPUT.WS_FUNC = " ";
            }
            if (BPRRHIS.REQ_TYPE == '1') {
                WS_OUTPUT.WS_TYPE = "LIBOR";
            } else if (BPRRHIS.REQ_TYPE == '2') {
                WS_OUTPUT.WS_TYPE = "HIBOR";
            } else if (BPRRHIS.REQ_TYPE == '3') {
                WS_OUTPUT.WS_TYPE = "PRIME";
            } else if (BPRRHIS.REQ_TYPE == '4') {
                WS_OUTPUT.WS_TYPE = "EXCHANGE";
            } else if (BPRRHIS.REQ_TYPE == '9') {
                WS_OUTPUT.WS_TYPE = "ALL RATE";
            } else {
                WS_OUTPUT.WS_TYPE = " ";
            }
            WS_OUTPUT.WS_TIME = BPRRHIS.TIME;
            df = new DecimalFormat("######");
            WS_OUTPUT.WS_REQ_TIME = df.format(BPRRHIS.REQ_TIME);
            if (BPRRHIS.RTN_FLG == '1') {
                WS_OUTPUT.WS_RTN = "ACCEPT";
            } else if (BPRRHIS.RTN_FLG == '2') {
                WS_OUTPUT.WS_RTN = "REJECT";
            } else {
                WS_OUTPUT.WS_RTN = " ";
            }
            R000_WRITE_QUEUE();
            if (pgmRtn) return;
            T000_FETCH_BPTRHIS();
            if (pgmRtn) return;
        }
        T000_CLOSE_BPTRHIS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB6610_AWA_6610.DATE_NO > 0) {
            if (!IBS.isNumeric(BPB6610_AWA_6610.DATE+"")) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_DATE_INVALID, BPB6610_AWA_6610.DATE_NO);
            }
        }
        if (BPB6610_AWA_6610.REQ_TYPE != ' ') {
            if (BPB6610_AWA_6610.REQ_TYPE != '1' 
                && BPB6610_AWA_6610.REQ_TYPE != '2' 
                && BPB6610_AWA_6610.REQ_TYPE != '3' 
                && BPB6610_AWA_6610.REQ_TYPE != '4' 
                && BPB6610_AWA_6610.REQ_TYPE != '9') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERR, BPB6610_AWA_6610.REQ_TYPE_NO);
            }
        }
    }
    public void B020_BRW_BPTRHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRRHIS);
        if (BPB6610_AWA_6610.DATE != 0) {
            if (BPB6610_AWA_6610.REQ_TYPE == ' ') {
                BPRRHIS.KEY.DATE = BPB6610_AWA_6610.DATE;
                T000_OPEN_BPTRHIS1();
                if (pgmRtn) return;
            } else {
                BPRRHIS.KEY.DATE = BPB6610_AWA_6610.DATE;
                BPRRHIS.REQ_TYPE = BPB6610_AWA_6610.REQ_TYPE;
                T000_OPEN_BPTRHIS2();
                if (pgmRtn) return;
            }
        } else {
            if (BPB6610_AWA_6610.REQ_TYPE == ' ') {
                T000_OPEN_BPTRHIS3();
                if (pgmRtn) return;
            } else {
                BPRRHIS.REQ_TYPE = BPB6610_AWA_6610.REQ_TYPE;
                T000_OPEN_BPTRHIS4();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_INIT_QUEUE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.MAX_COL_NO = 50;
        SCCMPAG.SCR_ROW_CNT = 15;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_WRITE_QUEUE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_LEN = 50;
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT);
        B_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, WS_OUTPUT);
    }
    public void T000_OPEN_BPTRHIS1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRRHIS.KEY.DATE);
        CEP.TRC(SCCGWA, BPRRHIS.KEY.DATE);
        BPTRHIS_BR.rp = new DBParm();
        BPTRHIS_BR.rp.TableName = "BPTRHIS";
        BPTRHIS_BR.rp.where = "'DATE' = :BPRRHIS.KEY.DATE";
        BPTRHIS_BR.rp.order = "DATE,JRNNO";
        IBS.STARTBR(SCCGWA, BPRRHIS, this, BPTRHIS_BR);
    }
    public void T000_OPEN_BPTRHIS2() throws IOException,SQLException,Exception {
        BPTRHIS_BR.rp = new DBParm();
        BPTRHIS_BR.rp.TableName = "BPTRHIS";
        BPTRHIS_BR.rp.where = "'DATE' = :BPRRHIS.KEY.DATE "
            + "AND REQ_TYPE = :BPRRHIS.REQ_TYPE";
        BPTRHIS_BR.rp.order = "DATE,JRNNO";
        IBS.STARTBR(SCCGWA, BPRRHIS, this, BPTRHIS_BR);
    }
    public void T000_OPEN_BPTRHIS3() throws IOException,SQLException,Exception {
        BPTRHIS_BR.rp = new DBParm();
        BPTRHIS_BR.rp.TableName = "BPTRHIS";
        BPTRHIS_BR.rp.order = "DATE,JRNNO";
        IBS.STARTBR(SCCGWA, BPRRHIS, BPTRHIS_BR);
    }
    public void T000_OPEN_BPTRHIS4() throws IOException,SQLException,Exception {
        BPTRHIS_BR.rp = new DBParm();
        BPTRHIS_BR.rp.TableName = "BPTRHIS";
        BPTRHIS_BR.rp.where = "REQ_TYPE = :BPRRHIS.REQ_TYPE";
        BPTRHIS_BR.rp.order = "DATE,JRNNO";
        IBS.STARTBR(SCCGWA, BPRRHIS, this, BPTRHIS_BR);
    }
    public void T000_FETCH_BPTRHIS() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRRHIS, this, BPTRHIS_BR);
    }
    public void T000_CLOSE_BPTRHIS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTRHIS_BR);
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
