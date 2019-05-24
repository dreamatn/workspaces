package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1400 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_CMP_PARM_TYPE_MAINT = "BP-MAINT-PARM-TYPE";
    String K_CMP_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String PGM_SCSSCKDT = "SCSSCKDT";
    BPOT1400_WS_ERR_MSG WS_ERR_MSG = new BPOT1400_WS_ERR_MSG();
    short WS_FLD_NO = 0;
    int WS_CNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRPARP BPRPARP = new BPRPARP();
    BPCRBPRP BPCRBPRP = new BPCRBPRP();
    BPCOBPRP BPCOBPRP = new BPCOBPRP();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    BPB1416_AWA_1416 BPB1416_AWA_1416;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT1400 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1416_AWA_1416>");
        BPB1416_AWA_1416 = (BPB1416_AWA_1416) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        IBS.init(SCCGWA, BPCRBPRP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_MAIN_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRBPRP);
        IBS.init(SCCGWA, BPRPARP);
        BPCRBPRP.LEN = 272;
        CEP.TRC(SCCGWA, BPCRBPRP.LEN);
        BPCRBPRP.PTR = BPRPARP;
        BPRPARP.KEY.TYPE = BPB1416_AWA_1416.PARP_TYP;
        CEP.TRC(SCCGWA, BPRPARP.KEY.TYPE);
        BPCRBPRP.FUNC = 'S';
        S000_CALL_BPZRBPRP();
        if (pgmRtn) return;
        R000_WRITE_TITLE();
        if (pgmRtn) return;
        while (BPCRBPRP.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            BPCRBPRP.FUNC = 'R';
            S000_CALL_BPZRBPRP();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCRBPRP.RETURN_INFO);
            if (BPCRBPRP.RETURN_INFO != 'N') {
                R000_OUTPUT_DATA_PROCESS();
                if (pgmRtn) return;
                R000_WRITE_TS();
                if (pgmRtn) return;
            }
        }
        BPCRBPRP.FUNC = 'E';
        S000_CALL_BPZRBPRP();
        if (pgmRtn) return;
    }
    public void R000_OUTPUT_DATA_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOBPRP);
        BPCOBPRP.DATA.KEY.TYPE = BPRPARP.KEY.TYPE;
        BPCOBPRP.DATA.NAME = BPRPARP.NAME;
        BPCOBPRP.DATA.CNAME = BPRPARP.CNAME;
        BPCOBPRP.DATA.DSTORE = BPRPARP.DSTORE;
        BPCOBPRP.DATA.NSTORE = BPRPARP.NSTORE;
        BPCOBPRP.DATA.SDESC = BPRPARP.SDESC;
        BPCOBPRP.DATA.CHK = BPRPARP.CHK;
        BPCOBPRP.DATA.ENA = BPRPARP.ENA;
        BPCOBPRP.DATA.STS = BPRPARP.STS;
        BPCOBPRP.DATA.ATTR = BPRPARP.ATTR;
        BPCOBPRP.DATA.CLASS = BPRPARP.CLASS;
        BPCOBPRP.DATA.DM_TYPE = BPRPARP.DM_TYPE;
        BPCOBPRP.DATA.MANT = BPRPARP.MANT;
        BPCOBPRP.DATA.USE_LVL = BPRPARP.USE_LVL;
        BPCOBPRP.DATA.STORE = BPRPARP.STORE;
        BPCOBPRP.DATA.DOWN_FLAG = BPRPARP.DOWN_FLAG;
        BPCOBPRP.DATA.HISYEAR = BPRPARP.HISYEAR;
        BPCOBPRP.DATA.DEL_FLG = BPRPARP.DEL_FLG;
        BPCOBPRP.DATA.DUP_DATE_FLG = BPRPARP.DUP_DATE_FLG;
        BPCOBPRP.DATA.PARM_VIEW1 = BPRPARP.PARM_VIEW1;
        BPCOBPRP.DATA.PARM_VIEW2 = BPRPARP.PARM_VIEW2;
        BPCOBPRP.DATA.PARM_VIEW3 = BPRPARP.PARM_VIEW3;
        BPCOBPRP.DATA.PARM_VIEW4 = BPRPARP.PARM_VIEW4;
        BPCOBPRP.DATA.PARM_VIEW5 = BPRPARP.PARM_VIEW5;
        BPCOBPRP.DATA.PARM_VIEW6 = BPRPARP.PARM_VIEW6;
        BPCOBPRP.DATA.PARM_VIEW7 = BPRPARP.PARM_VIEW7;
        BPCOBPRP.DATA.PARM_VIEW8 = BPRPARP.PARM_VIEW8;
        BPCOBPRP.DATA.PARM_VIEW9 = BPRPARP.PARM_VIEW9;
        BPCOBPRP.DATA.TXN_ID = BPRPARP.TXN_ID;
        BPCOBPRP.DATA.INQ_TXN = BPRPARP.INQ_TXN;
        BPCOBPRP.DATA.NXT_TXN = BPRPARP.NXT_TXN;
        BPCOBPRP.DATA.COPYBOOK = BPRPARP.COPYBOOK;
        BPCOBPRP.DATA.CHK_CPNT = BPRPARP.CHK_CPNT;
        BPCOBPRP.DATA.LEN = BPRPARP.LEN;
        BPCOBPRP.DATA.STSW = BPRPARP.STSW;
    }
    public void R000_WRITE_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 228;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_WRITE_TS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOBPRP);
        SCCMPAG.DATA_LEN = 228;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRBPRP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-MAIN-BRO-PARP", BPCRBPRP);
        CEP.TRC(SCCGWA, BPCRBPRP.RC);
        CEP.TRC(SCCGWA, BPCRBPRP.RETURN_INFO);
        if (BPCRBPRP.RETURN_INFO == 'N' 
            && BPCRBPRP.FUNC != 'R') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_TYPE_NOTFND, WS_ERR_MSG);
            WS_FLD_NO = BPB1416_AWA_1416.PARP_TYP_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_FLD_NO);
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
